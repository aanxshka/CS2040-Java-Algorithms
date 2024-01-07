import java.util.*;
import java.io.*;

public class Islands {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int rows = io.getInt();
        int cols = io.getInt();

        String[][] grid = new String[rows][cols];
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            String str = io.getWord();
            for(int j = 0; j < cols; j++) {
                String c = String.valueOf(str.charAt(j));
                grid[i][j] = c;
                visited[i][j] = false;
            }
        }

        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j].equals("L") && !visited[i][j]) {
                    count++;
                    visited[i][j] = true;
                    Queue<Vertex> lands = new ArrayDeque<Vertex>();
                    lands.add(new Vertex(i,j));
                    while (lands.size() > 0) {
                        Vertex land = lands.poll();
                        //check up 
                        if (land.r - 1 >= 0 && (grid[land.r - 1][land.c].equals("L") || grid[land.r - 1][land.c].equals("C"))
                        && !visited[land.r - 1][land.c]) {
                            lands.add(new Vertex(land.r -1, land.c));
                            visited[land.r - 1][land.c] = true;
                        }
                        //check down 
                        if (land.r + 1 < rows && (grid[land.r + 1][land.c].equals("L") || grid[land.r + 1][land.c].equals("C"))
                        && !visited[land.r + 1][land.c]) {
                            lands.add(new Vertex(land.r +1, land.c));
                            visited[land.r + 1][land.c] = true;
                        }
                        //check left
                        if (land.c - 1 >= 0 && (grid[land.r][land.c - 1].equals("L") || grid[land.r][land.c - 1].equals("C"))
                        && !visited[land.r][land.c - 1]) {
                            lands.add(new Vertex(land.r, land.c - 1));
                            visited[land.r][land.c - 1] = true;
                        }
                        //check right 
                        if (land.c + 1 < cols && (grid[land.r][land.c + 1].equals("L") || grid[land.r][land.c + 1].equals("C"))
                        && !visited[land.r][land.c + 1]) {
                            lands.add(new Vertex(land.r, land.c + 1));
                            visited[land.r][land.c + 1] = true;
                        }
                    }
                }
            }
        }

        io.print(count);
        io.close();
    }
    
    static class Vertex {
        int r;
        int c;

        Vertex(int r, int c) {
            this.r = r;
            this.c = c;
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
