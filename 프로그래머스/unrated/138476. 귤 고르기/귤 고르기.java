import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<tangerine.length; i++) {
            if (map.containsKey(tangerine[i])) {
                map.put(tangerine[i], map.get(tangerine[i])+1);
            } else {
                map.put(tangerine[i], 1);
            }
        }
        List<Integer> values = map.values().stream().sorted((i1, i2) -> i2 - i1).collect(Collectors.toList());
        for (Integer cnt : values) {
            if (k <= 0) break;
            k -= cnt;
            answer++;
        }
        return answer;
    }
}