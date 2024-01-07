/* PSUEDOCODE
 * while (true) {
    n = Read an integer from input
    if n == -1:
        break
    
    // Initialize data structures
    Create a 2D array adjMatrix of size n x n
    Create a boolean array valid of size n

    // Read adjacency matrix from input
    for i from 0 to n-1:
        line = Read a line of space-separated integers from input
        tokens = Split line into an array of integers
        for j from 0 to n-1:
            adjMatrix[i][j] = tokens[j]

    // Check for weak vertices
    for i from 0 to n-1:
        for j from 0 to n-1:
            if i == j:
                continue
            for k from 0 to n-1:
                if i == k or j == k:
                    continue
                if adjMatrix[i][j] == 1 and adjMatrix[i][k] == 1 and adjMatrix[j][k] == 1:
                    valid[i] = true
                    valid[j] = true
                    valid[k] = true

    // Output weak vertices
    for i from 0 to n-1:
        if not valid[i]:
            Write i to output
    
    Write a newline to output
}

Close the output stream
 */

import java.util.*;
import java.io.*;

public class WeakVertices {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        while (true) {
            int num = io.getInt();
            if (num == -1) {
                break;
            }
            int[][] adjMatrix = new int[num+1][num+1];
            boolean[] check = new boolean[num+1];

            for (int i = 1; i <= num; i++) {
                for (int j = 1; j<= num; j++) {
                    adjMatrix[i][j] = io.getInt();
                }

            }

            for (int i = 1; i <= num; i++) {
                for (int j = 1; j<=num; j++) {
                    if (i != j) {
                        for (int m = 1; m <= num; m++) {
                            if (i != m && j != m) {
                                if (adjMatrix[i][j] == 1 &&
                                    adjMatrix[i][m] == 1 &&
                                    adjMatrix[j][m] == 1) {
                                        check[i] = true;
                                        check[j] = true;
                                        check[m] = true;
                                    }
                            }
                        }
                    }
                }
            }

            
            for (int i = 1; i <=num; i++) {
                if (check[i] == false) {
                    io.write(i-1 + " ");
                }
            }
            io.write("\n");
        }
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
