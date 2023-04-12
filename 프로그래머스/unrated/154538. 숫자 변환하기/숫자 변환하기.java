import java.util.*;

class Solution {
    class Calc {
        int num;
        int cnt;
        
        public Calc (int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
    
    public int solution(int x, int y, int n) {
        if (x == y) return 0;
        
        Queue<Calc> q = new LinkedList<>();
        boolean[] visit = new boolean[y+1];
        q.add(new Calc(x, 0));
        visit[x] = true;
        while(!q.isEmpty()) {
            Calc now = q.remove();
            
            int next = now.num + n;
            if (next == y) return now.cnt+1;
            if (next < y && !visit[next]) {
                visit[next] = true;
                q.add(new Calc(next, now.cnt+1));
            }
            
            next = now.num*2;
            if (next == y) return now.cnt+1;
            if (next < y && !visit[next]) {
                visit[next] = true;
                q.add(new Calc(next, now.cnt+1));
            }

            next = now.num*3;
            if (next == y) return now.cnt+1;
            if (next < y && !visit[next]) {
                visit[next] = true;
                q.add(new Calc(next, now.cnt+1));
            }
        }
        return -1;
    }
}