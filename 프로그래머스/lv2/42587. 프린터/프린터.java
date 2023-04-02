import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i=0; i<priorities.length; i++) {
            pq.add(priorities[i]);
        }
        int cnt = 1;
        boolean[] check = new boolean[priorities.length];
        while (true) {
            for (int i=0; i<priorities.length; i++) {
                if (check[i]) continue;
                if (pq.peek() == priorities[i]) {
                    pq.remove();
                    check[i] = true;
                    if (i == location) return cnt;
                    cnt++;
                }
            }
        }
    }
}