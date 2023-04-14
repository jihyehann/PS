import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.awt.Point;
import static java.lang.Integer.parseInt;

public class Main {
    static int N;
    static int[][] mat;
    static List<Point> cloud = new ArrayList<>(); // 구름 위치

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
        cloud.add(new Point(N-1,0));
        cloud.add(new Point(N-1,1));
        cloud.add(new Point(N-2,0));
        cloud.add(new Point(N-2,1));
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
        for (Point p : cloud) {
            int nx = p.x + dir[d][0]*s;
            int ny = p.y + dir[d][1]*s;
            if (nx < 0) nx += N;
            else if (nx >= N) nx -= N;
            if (ny < 0) ny += N;
            else if (ny >= N) ny -= N;

            // 구름 이동
            p.x = nx;
            p.y = ny;

            // 비 내리기
            mat[nx][ny] += 1;
        }
    }

    static void copyWater() { // 물복사
        for (Point p : cloud) {
            int cnt = 0;
            for (int j=1; j<8; j+=2) { // 대각선
                int x = p.x + dir[j][0];
                int y = p.y + dir[j][1];
                if (x < 0 || x >= N || y < 0 || y >= N) continue;
                if (mat[x][y] > 0) cnt++;
            }
            mat[p.x][p.y] += cnt;
        }
    }

    static void addCloud() {
        Map<Point, Boolean> m = new HashMap<>();
        for (Point p : cloud) {
            m.put(p, true);
        }
        cloud.clear();
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (!m.containsKey(new Point(i,j)) && mat[i][j] >= 2) {
                    mat[i][j] -= 2;
                    cloud.add(new Point(i,j));
                }
            }
        }
    }
}
