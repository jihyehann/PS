import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int r;
    static int c;
    static char[][] map;
    static int[][] mv = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] rc = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        r = rc[0];
        c = rc[1];
        map = new char[r][c];

        Pair start = null;
        List<Pair> water = new ArrayList<>();
        for (int i=0; i<r; i++) {
            String row = br.readLine();
            for (int j=0; j<c; j++) {
                map[i][j] = row.charAt(j);
                if (map[i][j] == 'S') {
                    start = new Pair(i,j);
                } else if (map[i][j] == '*') {
                    water.add(new Pair(i,j));
                }
            }
        }
        br.close();

        int ans = solve(start, water);
        if (ans == -1) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(ans);
        }
    }

    private static int solve(Pair start, List<Pair> water) {
        Queue<Status> q = new LinkedList<>();
        q.add(new Status(start.x, start.y, 0));
        int prevCnt = 0;

        while (!q.isEmpty()) {
            Status now = q.peek();
            q.poll();

            // 물 퍼짐
            if (prevCnt < now.cnt) {
                spreadWater(water);
            }
            if (map[now.x][now.y] == '*') continue;

            prevCnt = now.cnt;
            for (int i = 0; i < 4; i++) {
                int nx = now.x + mv[i][0];
                int ny = now.y + mv[i][1];
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (map[nx][ny] == '.') {
                    map[nx][ny] = 'S';
                    q.add(new Status(nx, ny, prevCnt + 1));
                } else if (map[nx][ny] == 'D') {
                    return now.cnt + 1;
                }
            }
        }

        return -1;
    }

    private static void spreadWater(List<Pair> water) { // 물에 빠짐 여부
        int waterSize = water.size();
        for (int i=0; i<waterSize; i++) {
            Pair w = water.get(i);
            for (int j = 0; j < 4; j++) {
                int nx = w.x + mv[j][0];
                int ny = w.y + mv[j][1];
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (map[nx][ny] != '*' && map[nx][ny] != 'X' && map[nx][ny] != 'D') {
                    map[nx][ny] = '*';
                    water.add(new Pair(nx, ny));
                }
            }
        }
    }

    static class Status {
        int x;
        int y;
        int cnt;

        public Status(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
