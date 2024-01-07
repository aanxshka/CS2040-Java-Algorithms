import java.util.*;
import java.io.*;

public class Conformity {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int num = io.getInt();

        HashMap<String, Integer> hm = new HashMap<String, Integer>();

        for(int i = 0; i < num; i++) {
            ArrayList<Integer> ls = new ArrayList<>();
            for(int j = 0; j < 5; j++) {
                ls.add(io.getInt());
            }
            Collections.sort(ls);
            String str = "";
            for (int x : ls) {
                str += String.valueOf(x) + " ";
            }
            if (hm.containsKey(str)) {
                int curr = hm.get(str);
                hm.put(str, curr + 1);
            } else {
                hm.put(str, 1);
            }
        }
        int curr = 0;
        int freq = 0;
        for (String str : hm.keySet()) {
            if (hm.get(str) > curr) {
                curr = hm.get(str);
                freq = hm.get(str);
            } else if (hm.get(str) == curr) {
                freq += curr;
            }
        }


        io.write(String.valueOf(freq));
        io.close();

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
