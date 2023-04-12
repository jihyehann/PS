import java.util.*;
class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        Stack<Character> st = new Stack<>();
        for (char num : number.toCharArray()) {
            while (k > 0 && !st.empty() && st.peek() < num) {
                st.pop(); k--;
            }
            st.push(num);
        }
        while (k > 0) {
            st.pop(); k--;
        }
        while (!st.empty()) {
            answer.append(st.pop());
        }
        return answer.reverse().toString();
    }
}