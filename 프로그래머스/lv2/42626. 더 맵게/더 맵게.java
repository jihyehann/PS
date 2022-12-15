import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = Arrays.stream(scoville).boxed()
                .collect(Collectors.toCollection(PriorityQueue::new));
        int cnt = 0;
        while (pq.size() > 1) {
            if (pq.peek() >= K) {
                return cnt;
            }
            cnt++;
            int first = pq.poll();
            int second = pq.poll();
            int mix = first + second*2;
            pq.add(mix);
        }

        if (pq.size() == 1 && pq.peek() >= K) {
            return cnt;
        }
        return -1;
    }
}
        