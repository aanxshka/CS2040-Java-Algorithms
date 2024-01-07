import java.util.*;
import java.io.*;

public class LostMap {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int num = io.getInt();


        //create edgelist pq
        ArrayList<Edge> edges = new ArrayList<Edge>();
        //PriorityQueue<Edge> edges = new PriorityQueue<Edge>();
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                int x = io.getInt();
                edges.add(new Edge(i, j, x));
            }
        }
        Collections.sort(edges);
        

        //kruskal's algo
        UFDS ufds = new UFDS(num);
        for (Edge e : edges) {
            if(!ufds.isSameSet(e.u, e.v)) {
                ufds.unionSet(e.u,e.v);
                io.print((e.u+1) + " " + (e.v+1) + "\n");
            }
        }

        io.close();

    }

    static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge other) {
            return this.w - other.w;
        }

    }

    static class UFDS {
        int[] p;

        UFDS(int n) {
            this.p = new int[n];
            for (int i = 0; i < n; i++) {
                p[i] = i;
            }
        } 

        public int findSet(int i) {
            if (p[i] == i) {
                return i;
            } else {
                p[i] = findSet(p[i]);
                return p[i];
            }
        }

        public boolean isSameSet(int i,int j) {
            return findSet(i) == findSet(j);
        }

        public void unionSet(int i, int j) {
            if (!isSameSet(i,j)) {
                int x = findSet(i);
                int y = findSet(j);
                p[y] = x;
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
