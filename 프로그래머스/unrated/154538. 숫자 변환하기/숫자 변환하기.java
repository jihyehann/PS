import java.util.*;

class Solution {
    Queue<Calc> q = new LinkedList<>();
    boolean[] visit;
    
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
       
        q.add(new Calc(x, 0));
        visit = new boolean[y+1];
        visit[x] = true;
        while(!q.isEmpty()) {
            Calc now = q.remove();
            if (addQ(now.num + n, now.cnt+1, y)) return now.cnt+1;
            if (addQ(now.num * 2, now.cnt+1, y)) return now.cnt+1;
            if (addQ(now.num * 3, now.cnt+1, y)) return now.cnt+1;
        }
        return -1;
    }
    
    boolean addQ(int num, int cnt, int y) {
        if (num == y) return true;
        if (num < y && !visit[num]) {
            visit[num] = true;
            q.add(new Calc(num, cnt));
        }
        return false;
    }
}