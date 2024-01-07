import java.util.*;
import java.io.*;

public class AUF {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        while (io.hasMoreTokens()) {
            int num = io.getInt();
            int num_oper = io.getInt();

            UFDS ufds = new UFDS(num);

            for(int i = 0; i < num_oper; i++) {
                int oper = io.getInt();
                if (oper == 1) {
                    int x1 = io.getInt();
                    int x2 = io.getInt();
                    ufds.unionSet(x1, x2);
                } else if (oper == 2) {
                    int x1 = io.getInt();
                    int x2 = io.getInt();
                    ufds.move(x1,x2);
                } else if (oper == 3) {
                    int x = io.getInt();
                    io.write(ufds.getCount(x) + " " + ufds.getSum(x) + " \n");
                }
            }
        }
        io.close();
        
    }

    static class UFDS {

        int[] parent;
        int[] sets;
        long[] count;
        long[] sum;

        UFDS(int n) {
            this.parent = new int[n+1];
            this.sets = new int[n+1];
            this.count = new long[n+1];
            this.sum = new long[n+1];
            
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                sets[i] = i;
                count[i] = 1;
                sum[i] = i;
            }
        }

        int findSet(int i) {
            int newp = sets[i];
            while (newp != parent[newp]) {
                newp = parent[newp]; //find true parent
            }
            sets[i] = newp;
            return newp;
        }

        boolean isSameSet(int i, int j) {
            return findSet(i) == findSet(j);
        }

        void unionSet(int p, int q) {
            int pp = findSet(p);
            int pq = findSet(q);
            if (!isSameSet(p, q)) {
                parent[pp] = pq;
                sets[p] = pq;
                sum[pq] += sum[pp];
                count[pq] += count[pp];
            }
        }
    
    
        void move(int p, int q) {
            int pp = findSet(p);
            int pq = findSet(q);
            if (!isSameSet(p, q)) {
                sum[pq] += p;
                sum[pp] -= p;
                count[pq]++;
                count[pp]--;
                if (count[pp] == 0) { // If there are no elements left in the set of p
                    sum[pp] = 0;
                }
                sets[p] = pq;
            }
        }

        
        long getCount(int i) {
            return count[findSet(i)];
        }

        long getSum(int i) {
            return sum[findSet(i)];
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