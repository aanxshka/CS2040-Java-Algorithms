
import java.util.*;
import java.io.*;

public class JoinString {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int num = io.getInt();

        ArrayList<String> words = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            words.add(io.getWord());
        }

        HashMap<Integer, TailLinkedList> hm = new HashMap<Integer,TailLinkedList>();
        for (int i = 1; i <= num; i++) {
            TailLinkedList ls = new TailLinkedList();
            ls.addNode(new Node(i));
            hm.put(i, ls);
        }

        int last = 1;
        if (num > 1) {
            for (int i = 0; i < num - 1; i++) {
                int i1 = io.getInt();
                int i2 = io.getInt();
                last = i1;
                TailLinkedList list1 = hm.get(i1);
                TailLinkedList list2 = hm.get(i2);
                list1.addList(list2);
                hm.put(i1, list1);
            }
        }

        TailLinkedList res = hm.get(last);
        Node curr = res.head;
        while (curr != null) { 
            int index = curr.digit;
            io.write(words.get(index - 1));
            curr = curr.next;
        }

        io.close();

    }

    static class TailLinkedList {
        Node head;
        Node tail;
        int size;

        TailLinkedList() {
            this.size = 0;
        }

        void addNode(Node n) {
            n.addNext(this.head);
            this.head = n;
            this.tail = head;
            size ++;
        }

        void addList(TailLinkedList tll) {
            tail.next = tll.head;
            tll.head = null;
            tail = tll.tail;
            size += tll.size;
        }

    }

    static class Node {
        int digit;
        Node next;
    
        Node(int digit) {
            this.digit = digit;
            this.next = null;
        }
    
        void addNext(Node next) {
            if (this.next == null) {
                this.next = next;
            } else {
                this.next.addNext(next);
            }
        }
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
    