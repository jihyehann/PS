import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> user = new HashMap<>();
        int size = 0;
        for (String r : record) {
            String[] log = r.split(" ");
            if (!log[0].equals("Change")) {
                size++;
            }
            if (log[0].equals("Enter") || log[0].equals("Change")) {
                user.put(log[1], log[2]);
            } 
        }
    
        String[] answer = new String[size];
        int i = 0;
        for (String r : record) {
            if (r.startsWith("Change")) continue;
            String[] log = r.split(" ");
            if (log[0].equals("Enter")) {
                answer[i++] = user.get(log[1])+"님이 들어왔습니다.";
            } else {
                answer[i++] = user.get(log[1])+"님이 나갔습니다.";
            }
        }
        return answer;
    }
}