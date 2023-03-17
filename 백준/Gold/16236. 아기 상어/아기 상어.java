import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class Main {
    static int[][] mat;
    static int N;

    static class Shark implements Comparable<Shark> {
        int x;
        int y;
        int time;

        public Shark(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public int compareTo(Shark o) {
            if (this.time < o.time) return -1;
            if (this.time == o.time && this.x < o.x) return -1;
            if (this.time == o.time && this.x == o.x && this.y < o.y) return -1;
            return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = parseInt(br.readLine());
        mat = new int[N][N];
        Shark start = null;
        for (int i = 0; i < N; i++) {
            mat[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++) {
                if (mat[i][j] == 9) {
                    start = new Shark(i, j, 0);
                    mat[i][j] = 0;
                }
            }
        }
        int size = 2;
        int eatCnt = 0;
        int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        while (true) {
            Queue<Shark> q = new LinkedList<>();
            PriorityQueue<Shark> eatShark = new PriorityQueue<>();
            boolean[][] visit = new boolean[N][N];
            q.add(start);
            visit[start.x][start.y] = true;
            while (!q.isEmpty()) {
                Shark now = q.remove();
                if (!eatShark.isEmpty() && now.time >= eatShark.peek().time) {
                    break;
                }
                for (int i = 0; i < 4; i++) {
                    int nx = now.x + dir[i][0];
                    int ny = now.y + dir[i][1];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    if (!visit[nx][ny] && mat[nx][ny] <= size) {
                        visit[nx][ny] = true;
                        if (mat[nx][ny] >= 1 && mat[nx][ny] < size) { // 물고기 먹을 수 있는 경우
                            eatShark.add(new Shark(nx, ny, now.time + 1));
                        } else {
                            q.add(new Shark(nx, ny, now.time + 1));
                        }
                    }
                }
            }
            if (!eatShark.isEmpty()) {
                Shark next = eatShark.remove();
                mat[next.x][next.y] = 0;
                eatCnt++;
                start = next;
                if (eatCnt == size) {
                    size++;
                    eatCnt = 0;
                }
            } else {
                break;
            }
        }

        System.out.println(start.time);
    }
}
