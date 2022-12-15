import java.util.*;

class Solution {
    List<Integer> values;
    boolean[] visit = new boolean[30];

    public int solution(String[][] clothes) {
        int answer = 0;
        Map<String, Integer> cnt = new HashMap<>();
        for (String[] c: clothes) {
            if (cnt.containsKey(c[1])) {
                cnt.put(c[1], cnt.get(c[1])+1);
            } else {
                cnt.put(c[1], 1);
            }
        }
        values = new ArrayList<>(cnt.values());
        Arrays.fill(visit, false);
        for (int i=0; i<values.size(); i++) {
            visit[i] = true;
            answer += getSum(i, values.get(i));
            visit[i] = false;
        }
        return answer;
    }

    int getSum(int index, int product) {
        int sum = product;
        for (int i = index+1; i < values.size(); i++) {
            if (visit[i]) {
                continue;
            }
            visit[i] = true;
            sum += getSum(i, product*values.get(i));
            visit[i] = false;
        }
        return sum;
    }
}