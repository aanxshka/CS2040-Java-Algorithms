import java.util.*;
import java.io.*;

public class cannonball {  
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        double startx = io.getDouble();
        double starty = io.getDouble();
        Vertex start = new Vertex(startx, starty);

        double endx = io.getDouble();
        double endy = io.getDouble();
        Vertex end = new Vertex(endx, endy);

        int num = io.getInt();
        Vertex[] edges = new Vertex[num+2];
        edges[0] = start;
        edges[num + 1] = end;
        for (int i = 1; i <=num; i++) {
            edges[i] = new Vertex(io.getDouble(), io.getDouble());
        }
        
        ArrayList<ArrayList<Pair>> AL = new ArrayList<>();
        for (int i = 0; i < num + 2; i++) {
            AL.add(new ArrayList<>());
        }

        
        for (int i = 1; i < num + 2; i++) {
            double wt = (Math.hypot(edges[i].getx() - edges[0].getx(), edges[i].gety() - edges[0].gety()))/5.0;
            AL.get(0).add(new Pair(i, wt));
        }
        

        // Add edges considering cannons and their firing times
        for (int i = 1; i < num + 1; i++) {
            for (int j = 1; j < num + 2; j++) {
                double wt = weight(edges[i], edges[j]);
                AL.get(i).add(new Pair(j, wt));
            }
        }

        ArrayList<Double> dist = new ArrayList<>(Collections.nCopies(num + 2, Double.MAX_VALUE));
        dist.set(0, 0.0); 

        PriorityQueue<Pair> pq = new PriorityQueue<>(); 
        pq.offer(new Pair(0,0.0));

        while (!pq.isEmpty()) {                      
            Pair top = pq.poll();
            int i = top.first();
            double w = top.second(); 
            if (w > dist.get(i)) continue;
        
            for (Pair p : AL.get(i)) {        
                int v = p.first();
                double wt = p.second();
                if (dist.get(i)+wt >= dist.get(v)) continue; // not improving, skip
                dist.set(v, dist.get(i)+wt);
                pq.offer(new Pair(v, dist.get(v))); // enqueue better pair
            }
        }
        io.print(dist.get(num+1));
        io.close(); 
    }

    public static class Vertex {
        double x;
        double y;

        Vertex(double x, double y) {
            this.x = x;
            this.y = y;
        }

        double getx() {
            return x;
        }

        double gety() {
            return y;
        }
    }

    public static double weight(Vertex a, Vertex b) {
        double dist = distance(a,b);
        return Math.min(2 + (Math.abs(dist - 50)/5.0), dist/5.0);
    }

    public static double distance(Vertex a, Vertex b) {
        return Math.hypot(b.getx() - a.getx(), b.gety() - a.gety());
    }

    public static class Pair implements Comparable<Pair> {
        Integer _first; Double _second;
      
        public Pair(Integer f, Double s) {
          _first = f;
          _second = s;
        }
      
        @Override
        public int compareTo(Pair o) {
            return this.second().compareTo(o.second());
        }
      
        Integer first() { return _first; }
        Double second() { return _second; }
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