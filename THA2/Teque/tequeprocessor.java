import java.util.*;
import java.io.*;

public class tequeprocessor {
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
    int[] first;
    int[] second;
    int firstSize;
    int secondSize;
    int s1;
    int s2;

    teque() {
        first = new int[2000000]; 
        second = new int[2000000]; 
        firstSize = 0;
        secondSize = 0;
        s1 = 1000000;
        s2 = 1000000;
    }


    void pushback(int i) {
        //elements[firstSize + secondSize] = i;
        if (firstSize > secondSize) {
            second[s2 + secondSize] = i;
            secondSize++;
        //put i at back of second and move second[0] to back of first 
        } else if (firstSize == secondSize) {
            second[s2 + secondSize] = i;
            first[s1 + firstSize] = second[s2];
            s2++;
            firstSize++;
        }
    }


    void pushfront(int i) {
        //push forward all elements in first and insert i at start of first 
        if (firstSize > secondSize) {
            s1--;
            first[s1] = i;
            s2--;
            second[s2] = first[s1 + firstSize];
            secondSize++;
        } else {
            s1--;
            first[s1] = i;
            firstSize++;
        }
    }


    void pushmiddle(int i) {
        // add to start of second
        if (firstSize > secondSize) {
            s2--;
            second[s2] = i;
            secondSize++;
        } else {
            first[s1 + firstSize] = i;
            firstSize++;
        }
    }


    int get(int i) {
        if (i < firstSize) {
            return first[s1 + i];
        } else {
            return second[s2 + i - firstSize];
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
    