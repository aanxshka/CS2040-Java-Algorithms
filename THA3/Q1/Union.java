import java.util.*;

class Union {
    static class UFDS {
        int[] parent;
        long[] sum;
        long[] size;

        UFDS(int n) {
            parent = new int[2*n + 1];
            sum = new long[2*n + 1];
            size = new long[2*n + 1];
            
            for (int i = 1; i <= n; i++) {
                parent[i] = i + n;
            }

            for (int i = n + 1; i <= 2*n; i++) {
                parent[i] = i;
                size[i] = 1;
                sum[i] = i - n;
            }
        }

        public int findSet(int x) {
            if (parent[x] == x) {
                return x;
            } else {
                parent[x] = findSet(parent[x]);
                return parent[x];               
            }
        }
 
        public void unionSet(int p, int q) {
            int x = findSet(p);
            int y = findSet(q);
            if (x != y) {
                parent[x] = y;
                sum[y] += sum[x];
                size[y] += size[x];
            }
        }

        public void move(int p, int q) {
            int x  = findSet(p), y = findSet(q);
            if (x != y) {
                parent[p] = y;
                size[x]--;
                size[y]++;
                sum[x] -= p;
                sum[y] += p;
            }
        }

        public void query(int p) {
            int x = findSet(p);
            System.out.println(size[x] + " " + sum[x]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            UFDS ufds = new UFDS(n);
            for (int i = 0; i < m; i++) {
                int op = sc.nextInt();
                if (op == 1) {
                    ufds.unionSet(sc.nextInt(), sc.nextInt());
                } else if (op == 2) {
                    ufds.move(sc.nextInt(), sc.nextInt());
                } else {
                    ufds.query(sc.nextInt());
                }
            }
        }
        sc.close();
    }
}
