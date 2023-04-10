import java.util.*;
import java.awt.*;

class Solution {
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0,0));
        int[][] mv = {{1,0},{-1,0},{0,1},{0,-1}};
        while (!q.isEmpty()) {
            Point now = q.remove();
            for (int i=0; i<4; i++) {
                int nx = now.x + mv[i][0];
                int ny = now.y + mv[i][1];
                if (nx == n-1 && ny == m-1) {
                    return maps[now.x][now.y]+1;
                }
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (maps[nx][ny] == 0 || maps[nx][ny] > 1) continue;
                maps[nx][ny] = maps[now.x][now.y] + 1;
                q.add(new Point(nx, ny));
            }
        }
        return -1;
    }
}