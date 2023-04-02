import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Set<String> num = new HashSet<>();
        Arrays.sort(phone_book, (a,b) -> a.length() - b.length());
        for (int i=0; i<phone_book.length; i++) {
            for (int j=1; j<=phone_book[i].length(); j++) {
                if (num.contains(phone_book[i].substring(0, j))) return false;
            }
            num.add(phone_book[i]);
        }
        return true;
    }
}