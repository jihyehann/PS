import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] visit = new boolean[n][m];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0,0,1));
        visit[0][0] = true;
        int[][] mv = {{1,0},{-1,0},{0,1},{0,-1}};
        while (!q.isEmpty()) {
            Point now = q.remove();
            for (int i=0; i<4; i++) {
                int nx = now.x + mv[i][0];
                int ny = now.y + mv[i][1];
                if (nx == n-1 && ny == m-1) {
                    return now.cnt+1;
                }
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (visit[nx][ny] || maps[nx][ny] == 0) continue;
                visit[nx][ny] = true;
                q.add(new Point(nx, ny, now.cnt+1));
            }
        }
        return -1;
    }
    
    class Point {
        int x;
        int y;
        int cnt;
        
        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}