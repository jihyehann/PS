import java.util.*;

class Solution {
    Map<String, Integer> p = new HashMap<>();
    
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        for (int i=0; i<=discount.length-10; i++) {
            p.clear();
            for (int j=0; j<want.length; j++) {
                p.put(want[j], number[j]);
            }
            if (dfs(i, i+9, discount)) answer++;
        }
        return answer;
    }
    
    boolean dfs(int now, int end, String[] discount) {
        if (now > end) {
            return p.isEmpty();
        }
        if (p.containsKey(discount[now])) {
            if (p.get(discount[now]) > 1) {
                p.put(discount[now], p.get(discount[now])-1);
            } else {
                p.remove(discount[now]);
            }
            return dfs(now+1, end, discount);
        } 
        return false;
    }
}