import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : scoville) {
            pq.add(s);
        }
        int answer = 0;
        while (pq.size() >= 2) {
            if (pq.peek() >= K) {
                break;
            }
            answer++;
            int first = pq.remove();
            int second = pq.remove();
            pq.add(first + second*2);
        }
        if (pq.peek() < K) return -1;
        return answer;
    }
}