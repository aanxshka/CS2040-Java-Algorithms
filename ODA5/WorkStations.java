/* PSUEDOCODE 
 * 
 * create researcher class that has attributes, arrivalTime and stayTime
 * create researcher comparator to sort the workers 
 * 
 * Take in number of researchers 
 * Take in lockTime 
 * 
 * Create a PQ using ResearcherrComparator 
 * Create and insert each researcher into the PQ 
 * Initialise integer saves representing number of lockings Penelope saves herself 
 * 
 * Iterating through PQ,
 *      R = PQ.peek() //referring to the first researcher to arrive
 *      
 * 
 */
import java.util.*;
import java.io.*;

public class WorkStations {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
       
        int numOfWorkers = io.getInt();
        int lockTime = io.getInt();

        PriorityQueue<Integer> stations = new PriorityQueue<Integer>();
        PriorityQueue<Researcher> researchers = new PriorityQueue<Researcher>(numOfWorkers, new TComp());

        for (int i = 0; i < numOfWorkers; i++) {
            researchers.add(new Researcher(io.getInt(), io.getInt()));
        }

        int saves = 0;

        
        for (int i = 0; i < numOfWorkers; i++) {
            
            Researcher r = researchers.peek();
          
            //remove locked stations 
            while (stations.size() !=0 && stations.peek() + lockTime < r.getAT()) {
                stations.poll();
            }
            
            if (stations.size() != 0) {
                if (stations.peek() + lockTime >= r.getAT() && stations.peek() <= r.getAT()) {
                    saves ++;
                    stations.poll();
                    //System.out.print("+");
                }
            }
            
            stations.add(r.endTime());
            
            /* 
            for (int n : stations) {
                System.out.print(n + " ");
            }
            */

            researchers.poll();

        }

        io.write(String.valueOf(saves));
        io.close();
    }

    static class Researcher {
        int arrivalTime;
        int serviceTime;
    
        Researcher(int arrivalTime, int serviceTime) {
            this.arrivalTime = arrivalTime;
            this.serviceTime = serviceTime;
        }
    
        int endTime() {
            return this.arrivalTime + this.serviceTime;
        }

        int getAT() {
            return this.arrivalTime;
        }
    }

    static class TComp implements Comparator<Researcher> {
        @Override 
        public int compare (Researcher r1, Researcher r2) {
            if (r1.arrivalTime < r2.arrivalTime) {
                return -1;
            } else if (r1.arrivalTime > r2.arrivalTime) {
                return 1;
            } else {
                return 0;
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