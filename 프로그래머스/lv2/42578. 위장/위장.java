import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String[] c : clothes) {
            if (cnt.containsKey(c[1])) {
                cnt.put(c[1], cnt.get(c[1]) + 1);
            } else {
                cnt.put(c[1], 1);
            }
        }
        int answer = 1;
        List<Integer> values = new ArrayList<>(cnt.values());
        for (int i = 0; i < values.size(); i++) {
            answer *= (values.get(i)+1);
        }
        return answer-1;
    }
}