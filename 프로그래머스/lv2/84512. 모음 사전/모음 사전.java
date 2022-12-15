import java.util.HashMap;

class Solution {
    public int solution(String word) {
        int answer = 0;
        HashMap<Character, Integer> map = new HashMap<>() {{
            put('A', 0);
            put('E', 1);
            put('I', 2);
            put('O', 3);
            put('U', 4);
        }};

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == 'A') {
                answer++;
                continue;
            }
          
            int sum = 0;
            for (int j=i; j<=4; j++) {
                sum += Math.pow(5, 4-j);
            }
            answer += (map.get(word.charAt(i))*sum+1);
        }
        return answer;
    }
}