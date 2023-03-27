import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static int R, C;
    static char[][] mat;
    static List<Point> cluster = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        mat = new char[R+1][C];
        for (int i = R; i >= 1; i--) {
            mat[i] = br.readLine().toCharArray();
        }

        int N = Integer.parseInt(br.readLine());
        input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(input[i]);
            int c = destroy(i, h);
            if (c < 0 || c >= C) continue; // 파괴된 미네랄이 없는 경우
            fall(h, c);
        }
        print();
    }

    static int destroy(int turn, int h) {
        int c;
        if (turn % 2 == 0) { // 왼쪽에서 던짐
            for (c = 0; c < C; c++) {
                if (mat[h][c] == 'x') {
                    mat[h][c] = '.';
                    break;
                }
            }
        } else { // 오른쪽에서 던짐
            for (c = C-1; c >= 0; c--) {
                if (mat[h][c] == 'x') {
                    mat[h][c] = '.';
                    break;
                }
            }
        }
        return c;
    }

    static int[][] mv = {{1,0},{-1,0},{0,1},{0,-1}};
    static void fall(int x, int y) {
        for (int dir = 0; dir < 4; dir++) {
            cluster.clear();
            Queue<Point> q = new LinkedList<>();
            Point next = new Point(x+mv[dir][0], y+mv[dir][1]);
            if (next.x < 1 || next.x > R || next.y < 0 || next.y >= C) continue;
            if (mat[next.x][next.y] == '.') continue;

            q.add(next);
            cluster.add(next);
            boolean[][] visit = new boolean[R+1][C];
            visit[next.x][next.y] = true;
            boolean stop = false;
            while(!q.isEmpty()) {
                Point now = q.remove();
                if (now.x == 1) {
                    stop = true;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = now.x + mv[i][0];
                    int ny = now.y + mv[i][1];
                    if (nx < 1 || nx > R || ny < 0 || ny >= C) continue;
                    if (visit[nx][ny]) continue;
                    if (mat[nx][ny] == '.') continue;
                    visit[nx][ny] = true;
                    q.add(new Point(nx, ny));
                    cluster.add(new Point(nx, ny));
                }
            }

            int move = 0;
            while (!stop) {
                move++;
                for (Point p : cluster) {
                    if (p.x - move == 1 || (mat[p.x - move - 1][p.y] == 'x' && !cluster.contains(new Point(p.x - move -1, p.y)))) {
                        stop = true;
                        break;
                    }
                }
            }
            for (Point p : cluster) {
                mat[p.x][p.y] = '.';
            }
            for (Point p : cluster) {
                mat[p.x - move][p.y] = 'x';
                p.x -= move;
            }
        }
    }

    static void print() {
        for (int i = R; i >= 1; i--) {
            System.out.println(mat[i]);
        }
    }
}
