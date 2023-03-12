import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class Main {
    static int N;
    static int M;
    static int[][] circles;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = parseInt(input[0]);
        M = parseInt(input[1]);
        int T = parseInt(input[2]);
        circles = new int[N+1][M];
        for (int i=1; i<=N; i++) {
            String[] circle = br.readLine().split(" ");
            for (int j=0; j<circle.length; j++) {
                circles[i][j] = parseInt(circle[j]);
            }
        }
        for (int i=0; i<T; i++) {
            String[] t = br.readLine().split(" ");
            int x = parseInt(t[0]); // 원판 번호 (배수)
            int d = parseInt(t[1]); // 방향
            int k = parseInt(t[2]); // 칸
            rotate(x,d,k);
            
            boolean[][] visit = new boolean[N + 1][M];
            boolean exist = false;
            for (int j=1; j<=N; j++) {
                for (int l=0; l<M; l++) {
                    if (!visit[j][l] && circles[j][l] != 0) {
                        exist = exist | findSameNumber(j, l, visit);
                    }
                }
            }
            if (!exist) {
                change();
            }
        }

        System.out.println(getSumAndCnt()[0]);
    }

    private static int[] getSumAndCnt() {
        int sum = 0;
        int cnt = 0;
        for (int i=1; i<=N; i++) {
            for (int j=0; j<M; j++) {
                if (circles[i][j] != 0) {
                    sum += circles[i][j];
                    cnt++;
                }
            }
        }
        return new int[] {sum, cnt};
    }

    private static void rotate(int x, int d, int k) {
        int[] tmp = new int[M];
        int step = (d == 0) ? k : M - k;
        for (int i=x; i<=N; i+=x) {
            for (int j=0; j<M; j++) {
                int pos = (j + step) % M;
                tmp[pos] = circles[i][j];
            }
            for (int j=0; j<M; j++) {
                circles[i][j] = tmp[j];
            }
        }
    }

    // x번호 원판에서 인접한 같은 수 찾아 지운다.
    private static boolean findSameNumber(int x, int y, boolean[][] visit) {
        visit[x][y] = true;
        boolean same = false;
        int[][] mv = {{0,1}, {0,M-1}, {-1,0},{1,0}};
        for (int i = 0; i < 4; i++) {
            int nx = x + mv[i][0];
            int ny = (y + mv[i][1]) % M;
            if (nx < 1 || nx > N) {
                continue;
            }
            if (!visit[nx][ny] && circles[x][y] == circles[nx][ny]) {
                findSameNumber(nx, ny, visit);
                circles[nx][ny] = 0;
                same = true;
            }
        }
        if (same) {
            circles[x][y] = 0;
        }
        return same;
    }

    private static void change() {
        int[] sumAndCnt = getSumAndCnt();
        int sum = sumAndCnt[0];
        int cnt = sumAndCnt[1];
        if (cnt == 0) return;

        int avg = sum / cnt;
        for (int i=1; i<=N; i++) {
            for (int j=0; j<M; j++) {
                if (circles[i][j] == 0) {
                    continue;
                }
                if (circles[i][j] > avg) {
                    circles[i][j]--;
                } else if (circles[i][j] < avg) {
                    circles[i][j]++;
                } else if (sum % cnt != 0) {
                    circles[i][j]++;
                }
            }
        }
    }
}
