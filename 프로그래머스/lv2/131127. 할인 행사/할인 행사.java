import java.util.*;

class Solution {
    Map<String, Integer> p = new HashMap<>();
    
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        for (int i=0; i<10; i++) {
            p.put(discount[i], p.getOrDefault(discount[i], 0)+1);
        }
        if (check(want, number)) answer++;
        for (int i=10; i<discount.length; i++) {
            p.put(discount[i-10], p.getOrDefault(discount[i-10], 0)-1);
            p.put(discount[i], p.getOrDefault(discount[i], 0)+1);
            if (check(want, number)) answer++;
        }
        return answer;
    }
    
    boolean check(String[] want, int[] number) {
        for (int i=0; i<want.length; i++) {
            if (p.getOrDefault(want[i], 0) < number[i]) return false;
        }
        return true;
    }
}