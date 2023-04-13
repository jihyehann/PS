import java.util.*;
class Solution {
    public int[] solution(int[] sequence, int k) {
        Queue<Integer> q = new LinkedList<>();
        int sum = 0;
        int[] answer = new int[] {0,sequence.length-1};
        for (int i=0; i<sequence.length; i++) {
            sum += sequence[i];
            q.add(sequence[i]);
            while (!q.isEmpty() && sum >= k) {
                if (sum == k && q.size() < answer[1]-answer[0]+1) {
                    answer[0] = i-q.size()+1;
                    answer[1] = i;
                    if (answer[0] == answer[1]) return answer;
                }
                sum -= q.remove();
            }
        }
        return answer;
    }
}