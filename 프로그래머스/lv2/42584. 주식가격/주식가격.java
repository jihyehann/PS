import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> st = new Stack<>();
        for (int i=0; i<prices.length; i++) {
            while (!st.isEmpty() && prices[i] < prices[st.peek()]) {
                int j = st.pop();
                answer[j] = i - j;
            }
            st.push(i);
        }
        while (!st.isEmpty()) {
            int i = st.pop();
            answer[i] = prices.length - 1 - i;
        }
        
        return answer;
    }
}