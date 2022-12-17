import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {

    public int solution(int n, int k, int[] enemy) {
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i=0; i<enemy.length; i++) {
            n -= enemy[i];
            pq.add(enemy[i]);
            if (n < 0) {
                if (k > 0 && !pq.isEmpty()) {
                    n += pq.poll();
                    k--;
                } else {
                    return i;
                }
            }
        }
        return enemy.length;
    }
}
