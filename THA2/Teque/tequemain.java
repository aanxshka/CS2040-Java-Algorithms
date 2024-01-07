import java.util.*;
import java.io.*;

public class tequemain {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int num = io.getInt();

        teque tq = new teque();

        for (int i = 0; i < num; i++) {
            String command = io.getWord();
            int input = io.getInt();
            if (command.equals("push_back")) {
                tq.pushback(input);
            } else if (command.equals("push_front")) {
                tq.pushfront(input);
            } else if (command.equals("push_middle")) {
                tq.pushmiddle(input);
            } else {
                io.println(tq.get(input));
            }
        }
        io.close();
    }

}


class teque {
    DoublyLinkedList first;
    DoublyLinkedList second;

    teque() {
        first = new DoublyLinkedList();
        second = new DoublyLinkedList();
    }
        
    void pushback(int i) {
        DListNode n = new DListNode(i);
        //if teque is empty, add n to first 
        if (first.size == 0) {
            first.addFront(n);
            //System.out.println(i);
        //if first has one more than second, just add to second 
        } else if (first.size > second.size) {
            second.addBack(n);
        //if first == second, add to second and shift second.head to end of first so as to maintain sizes
        } else if (first.size == second.size) {
            second.addBack(n);
            DListNode shift = second.head;
            second.removeFront();
            first.addBack(shift);
        }
    }

    void pushfront(int i) {
        DListNode n = new DListNode(i);
        //moving last element of first to become first element of second 
        if (first.size > second.size) {
            DListNode shift = first.tail;
            first.removeBack();
            second.addFront(shift);
        }
        //add element to the start of first 
        first.addFront(n);
        //System.out.println(i);

    }

    void pushmiddle(int i) {
        DListNode n = new DListNode(i);
        if (first.size == second.size) {
            //add node to end of first
            first.addBack(n);
            //System.out.println(i);
        } else if (first.size > second.size) {
            //add node to start of second;
            second.addFront(n);
            //System.out.println(i);
        }
    }

    int get(int i) {
        if (i < first.size) {
            int count = 0;
            DListNode curr = first.head;
            while (count < i) {
                curr = curr.next;
                count++;
            }
            return curr.element;

        } else {
            int count = first.size;
            DListNode curr = second.head;
            while (count < i) {
                curr = curr.next;
                count++;
            }
            //System.out.println(curr.element);
            return curr.element;
        }
    }

}

class DoublyLinkedList {
    DListNode head;
    DListNode tail;
    int size;

    DoublyLinkedList() {
        this.size = 0;
    }

    void addBack(DListNode n) {
        if (size == 0) {
            this.head = n;
            this.tail = n;
        } else {
            DListNode oldtail = this.tail;
            oldtail.next = n;
            n.prev = oldtail; 
            this.tail = n;
        }
        size ++;
    }

    void addFront(DListNode n) {
        if (size == 0) {
            this.head = n;
            this.tail = n;
        } else {
            DListNode oldhead = this.head;
            oldhead.prev = n;
            n.next = oldhead;
            this.head = n;
        }
        size++;
    }

    void removeFront() {
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        } else {
            DListNode oldhead = this.head;
            DListNode newhead = oldhead.next;
            oldhead.next = null;
            newhead.prev = null;
            this.head = newhead;
        }
        this.size--;
    }

    void removeBack() {
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        } else {
            DListNode oldtail = this.tail;
            DListNode newtail = oldtail.prev;
            oldtail.prev = null;
            newtail.next = null;
            this.tail = newtail;
        }
        this.size--;
    }

    int getSize() {
        return this.size;
    }

}

class DListNode {
	/* attributes */
	public int element;
	public DListNode next; 
	public DListNode prev;

	/* constructors */
	public DListNode(int item) {
		this(item, null, null);
	}

	public DListNode(int item, DListNode p, DListNode n) {
		element = item; 
		prev = p;
		next = n;
	}

	/* get the prev DListNode */
	public DListNode getPrev() {
		return prev;
	}

	/* get the next DListNode */
	public DListNode getNext() {
		return next;
	}

	/* get the element of the DListNode */
	public int getElement() {
		return element;
	}

	/* set the prev reference */
	public void setPrev(DListNode p) {
		prev = p;
	}

	/* set the next reference */
	public void setNext(DListNode n) {
		next = n;
	}

}


class Kattio extends PrintWriter {
    
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public boolean hasMoreTokens() {
    return peekToken() != null;
    }
    
    public int getInt() {
        return Integer.parseInt(nextToken());
    }
    
    public double getDouble() {
        return Double.parseDouble(nextToken());
    }
    
    public long getLong() {
        return Long.parseLong(nextToken());
    }
    
    public String getWord() {
        return nextToken();
    }
   
    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;
   
    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
            token = st.nextToken();
             } catch (IOException e) { }
        return token;
    }
    
    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
    
    }
    