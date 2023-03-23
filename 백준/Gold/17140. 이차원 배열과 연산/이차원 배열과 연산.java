import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int R = 3;
    static int C = 3;
    static int[][] mat = new int[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int r = Integer.parseInt(input[0]);
        int c = Integer.parseInt(input[1]);
        int k = Integer.parseInt(input[2]);

        for (int i=0; i<3; i++) {
            System.arraycopy(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(), 0, mat[i], 0, 3);
        }

        for (int i=0; i<100; i++) {
            if (mat[r-1][c-1] == k) {
                System.out.println(i);
                return;
            }
            sort();
        }
        if (mat[r-1][c-1] == k) {
            System.out.println(100);
        } else {
            System.out.println(-1);
        }
    }

    private static void sort() {
        if (R >= C) rSort();
        else cSort();
    }

    private static void rSort() {
        for (int i=0; i<R; i++) {
            int[] count = new int[101];
            for (int j=0; j<C; j++) {
                if (mat[i][j] == 0) continue;
                count[mat[i][j]]++;
            }
            PriorityQueue<Pair> pq = new PriorityQueue<>();
            for (int j = 1; j <= 100; j++) {
                if (count[j] == 0) continue;
                pq.add(new Pair(j, count[j]));
                if (pq.size() == 50) break;
            }
            int max = Math.min(50, pq.size());
            for (int j = 0; j < max; j++) {
                Pair e = pq.poll();
                mat[i][j*2] = e.num;
                mat[i][j*2+1] = e.cnt;
            }
            for (int j = max*2; j < C; j++) {
                mat[i][j] = 0;
            }
            C = Math.max(max*2, C);
        }
    }

    private static void cSort() {
        for (int j=0; j<C; j++) {
            int[] count = new int[101];
            for (int i=0; i<R; i++) {
                if (mat[i][j] == 0) continue;
                count[mat[i][j]]++;
            }
            PriorityQueue<Pair> pq = new PriorityQueue<>();
            for (int i = 1; i <= 100; i++) {
                if (count[i] == 0) continue;
                pq.add(new Pair(i, count[i]));
                if (pq.size() == 50) break;
            }
            int max = Math.min(50, pq.size());
            for (int i = 0; i < max; i++) {
                Pair e = pq.poll();
                mat[i*2][j] = e.num;
                mat[i*2+1][j] = e.cnt;
            }
            for (int i = max*2; i < R; i++) {
                mat[i][j] = 0;
            }
            R = Math.max(max*2, R);
        }
    }

    static class Pair implements Comparable<Pair> {
        int num;
        int cnt;

        public Pair(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Pair o) {
            if (cnt == o.cnt) {
                return num - o.num;
            }
            return cnt - o.cnt;
        }
    }
}
