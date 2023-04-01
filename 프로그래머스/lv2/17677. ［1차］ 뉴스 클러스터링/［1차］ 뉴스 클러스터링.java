import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String str1, String str2) {
        Map<String, AB> ab = new HashMap<>();
        int cnt = 0;
        for (int i=0; i<str1.length()-1; i++) {
            if (!isAlpha(str1.charAt(i)) || !isAlpha(str1.charAt(i+1))) continue;
            String str = str1.substring(i, i+2).toLowerCase();
            cnt++;
            if (ab.containsKey(str)) {
                ab.get(str).a++;
            } else {
                ab.put(str, new AB(1, 0));
            }
        }
        for (int i=0; i<str2.length()-1; i++) {
            if (!isAlpha(str2.charAt(i)) || !isAlpha(str2.charAt(i+1))) continue;
            String str = str2.substring(i, i+2).toLowerCase();
            cnt++;
            if (ab.containsKey(str)) {
                ab.get(str).b++;
            } else {
                ab.put(str, new AB(0, 1));
            }
        }
        if (cnt == 0) return 65536;

        int union = 0;
        int intersection = 0;
        for (String key : ab.keySet()) {
            union += ab.get(key).max();
            intersection += ab.get(key).min();
        }
        
        return intersection*65536/union;
    }

    class AB {
        int a;
        int b;
        public AB(int a, int b) {
            this.a = a;
            this.b = b;
        }
        int max() {
            return Math.max(a, b);
        }
        int min() {
            return Math.min(a, b);
        }
    }
    boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
}
