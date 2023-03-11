import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int[][] mat;
    static int airCleaner = 50;
    static int R,C;
    static int[][] mv = {{0,1}, {-1,0}, {0,-1}, {1,0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        int T = Integer.parseInt(input[2]);
        mat = new int[R][C];
        for (int i = 0; i < R; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                mat[i][j] = Integer.parseInt(row[j]);
            }
            if (mat[i][0] == -1) {
                airCleaner = Math.min(airCleaner, i);
            }
        }

        for (int i = 0; i < T; i++) {
            spread();
            clean();
        }

        System.out.println(countDust());
    }

    private static int countDust() {
        int sum = 2; // 공기청정기의 합이 -2
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sum += mat[i][j];
            }
        }
        return sum;
    }

    private static void spread() {
        List<Pair> dust = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (mat[i][j] > 0) {
                    dust.add(new Pair(i,j, mat[i][j]/5));
                }
            }
        }
        for (Pair now : dust) {
            for (int i = 0; i < 4; i++) {
                int nx = now.x + mv[i][0];
                int ny = now.y + mv[i][1];

                if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                    continue;
                }
                if (mat[nx][ny] == -1) {
                    continue;
                }
                mat[nx][ny] += now.amount;
                mat[now.x][now.y] -= now.amount;
            }
        }
    }

    static class Pair {
        int x;
        int y;
        int amount;

        public Pair(int x, int y, int amount) {
            this.x = x;
            this.y = y;
            this.amount = amount;
        }
    }

    private static void clean() {
        // 위
        int next = mat[airCleaner][1];
        next = right(airCleaner, next);
        next = up(airCleaner-1, 0, C-1, next);
        next = left(0, next);
        down(1, airCleaner-1, 0, next);

        // 아래
        next = mat[airCleaner+1][1];
        next = right(airCleaner+1, next);
        next = down(airCleaner+2, R-1, C-1, next);
        next = left(R-1, next);
        up(R-2, airCleaner+2, 0, next);
    }

    private static int up(int sRow, int dRow, int col, int next) {
        for (int i = sRow; i >= dRow; i--) {
            int tmp = mat[i][col];
            mat[i][col] = next;
            next = tmp;
        }
        return next;
    }

    private static int left(int row, int next) {
        for (int j = C-2; j >= 0; j--) {
            int tmp = mat[row][j];
            mat[row][j] = next;
            next = tmp;
        }
        return next;
    }

    private static int down(int sRow, int dRow, int col, int next) {
        for (int i = sRow; i <= dRow; i++) {
            int tmp = mat[i][col];
            mat[i][col] = next;
            next = tmp;
        }
        return next;
    }

    private static int right(int row, int next) {
        for (int j = 2; j <= C-1; j++) {
            int tmp = mat[row][j];
            mat[row][j] = next;
            next = tmp;
        }
        mat[row][1] = 0; // 공기청정기 옆은 0
        return next;
    }
}
