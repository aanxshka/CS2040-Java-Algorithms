/* 
Pick the quest with the largest energy cost that is <= the currently available energy. Deduct the energy cost from the currently available energy, and add the gold reward to the answer
If tied by energy cost, pick the quest with the largest gold reward
Repeat until no more quests can be picked

*/

import java.util.*;
import java.io.*;

public class KQ {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int num = io.getInt();

        TreeMap<Quest,Integer> tm = new TreeMap<Quest,Integer>();
        for (int i = 0; i < num; i++) {
            String oper = io.getWord();

            if (oper.equals("add")) {
                int energy = io.getInt();
                int gold = io.getInt();
                Quest q = new Quest(energy, gold, i);
                tm.put(q, tm.getOrDefault(q,0) + 1);

            } else if (oper.equals("query")) {
                int energy = io.getInt();
                long totalGold = 0;

                Map.Entry<Quest, Integer> temp = tm.floorEntry(new Quest(energy, Integer.MAX_VALUE, Integer.MAX_VALUE));
                
                while (temp!=null && !tm.isEmpty()) {
                    Map.Entry<Quest, Integer> maxEntry = tm.floorEntry(new Quest(energy, Integer.MAX_VALUE, Integer.MAX_VALUE));
                    
                    if (maxEntry != null) {
                        Quest q = maxEntry.getKey();
                        int maxE = q.energy;
                        int maxG = q.gold;

                        totalGold += maxG;
                        energy -= maxE;

                        int rem = tm.get(q) - 1;
                        if (rem > 0) {
                            tm.put(q,rem);
                        } else {
                            tm.remove(q);
                        }
                    }

                    temp = tm.floorEntry(new Quest(energy, Integer.MAX_VALUE, Integer.MAX_VALUE));
                }
                io.println(totalGold);
            }
        }
        io.close();
    }


    static class Quest implements Comparable<Quest>{
        int energy;
        int gold;
        int id;

        Quest(int energy, int gold, int id) {
            this.energy = energy;
            this.gold = gold;
            this.id = id;
        }

        @Override 
        public int compareTo(Quest other) {
            if (energy == other.energy) {
                if (gold == other.gold) {
                    return Integer.compare(other.id, id);
                }
                return Integer.compare(gold, other.gold);
            } 
            return Integer.compare(energy, other.energy);
            
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