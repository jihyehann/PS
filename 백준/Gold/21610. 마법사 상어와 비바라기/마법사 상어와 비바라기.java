import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;

public class Main {
    static int N;
    static int[][] mat;
    static boolean[][] cloud; // 구름 위치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = parseInt(input[0]);
        int M = parseInt(input[1]);
        mat = new int[N][N];
        for (int i=0; i<N; i++) {
            input = br.readLine().split(" ");
            for (int j=0; j<N; j++) {
                mat[i][j] = parseInt(input[j]);
            }
        }
        cloud = new boolean[N][N];
        cloud[N-1][0] = true;
        cloud[N-1][1] = true;
        cloud[N-2][0] = true;
        cloud[N-2][1] = true;
        for (int i=0; i<M; i++) {
            input = br.readLine().split(" ");
            int d = parseInt(input[0])-1;
            int s = parseInt(input[1])%N;
            move(d,s);
            copyWater();
            addCloud();
        }

        int answer = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                answer += mat[i][j];
            }
        }
        System.out.println(answer);
    }

    static int[][] dir = {{0,-1}, {-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}}; // 홀수는 대각선
    static void move(int d, int s) {
        boolean[][] next = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!cloud[i][j]) continue;
                int nx = i + dir[d][0]*s;
                int ny = j + dir[d][1]*s;
                if (nx < 0) nx += N;
                else if (nx >= N) nx -= N;
                if (ny < 0) ny += N;
                else if (ny >= N) ny -= N;

                // 구름 이동
                next[nx][ny] = true;

                // 비 내리기
                mat[nx][ny] += 1;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cloud[i][j] = next[i][j];
            }
        }
    }

    static void copyWater() { // 물복사
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 0;
                if (!cloud[i][j]) continue;
                for (int k=1; k<8; k+=2) {
                    int x = i + dir[k][0];
                    int y = j + dir[k][1];
                    if (x < 0 || x >= N || y < 0 || y >= N) continue;
                    if (mat[x][y] > 0) cnt++;
                }
                mat[i][j] += cnt;
            }
        }
    }

    static void addCloud() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (!cloud[i][j] && mat[i][j] >= 2) {
                    mat[i][j] -= 2;
                    cloud[i][j] = true;
                } else {
                    cloud[i][j] = false;
                }
            }
        }
    }
}
