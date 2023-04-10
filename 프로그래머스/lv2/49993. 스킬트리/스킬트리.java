import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        Map<Character, Integer> m = new HashMap<>();
        for (int i=0; i<skill.length(); i++) {
            m.put(skill.charAt(i), i+1);
        }
        int answer = skill_trees.length;
        for (String st : skill_trees) {
            int now = 0;
            for (int i=0; i<st.length(); i++) {
                if (m.containsKey(st.charAt(i))) {
                    if (now != m.get(st.charAt(i))-1) {
                        answer--;
                        break;
                    }
                    now = m.get(st.charAt(i));
                }
            }
        }
        return answer;
    }
}