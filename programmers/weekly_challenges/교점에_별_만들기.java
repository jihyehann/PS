import java.util.HashSet;
import java.util.Set;

import static java.lang.Long.*;

class Solution {
    public String[] solution(int[][] line) {
        String[] answer = {};
        int size = line.length;
        Set<Point> set = new HashSet<>();
        long minX = MAX_VALUE, minY = MAX_VALUE, maxX = MIN_VALUE, maxY = MIN_VALUE;
        for (int i = 0; i < size - 1; i++) {
            long a = line[i][0], b = line[i][1], e = line[i][2];
            for (int j = i + 1; j < size; j++) {
                long c = line[j][0], d = line[j][1], f = line[j][2];

                long par = a*d - b*c;
                if (par == 0) continue;
                long x = b*f - e*d;
                if(x % par != 0) continue;
                long y = e*c - a*f;
                if(y % par != 0) continue;
                x /= par; y /= par;

                minX = Math.min(minX, x);
                minY = Math.min(minY, y);
                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);

                set.add(new Point(x,y));
            }
        }
        int width = (int) (maxX - minX) + 1;
        int height = (int) (maxY - minY) + 1;
        String dot = ".".repeat (width);
        answer = new String[height];
        for(int i=0; i<height; i++) answer[i] = dot;
        for (Point p : set) {
            int x = (int) (p.x - minX);
            int y = (int) (maxY - p.y);
            answer[y] = answer[y].substring(0,x)+'*'+answer[y].substring(x+1);
        }
        return answer;
    }

    public static class Point {
        long x;
        long y;
        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}
