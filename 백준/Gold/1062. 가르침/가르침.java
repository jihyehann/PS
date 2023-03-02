import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static List<String> words = new ArrayList<>();
    static boolean[] visit = new boolean[26];
    static int k;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nk = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nk[0];
        k = nk[1];

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            word = word.substring(4, word.length()-4);
            words.add(word);
        }

        if (k < 5) {
            System.out.println(0);
            return;
        }
        if(k == 26) {
            System.out.println(nk[0]);
            return;
        }

        visit['a'-'a'] = true;
        visit['n'-'a'] = true;
        visit['t'-'a'] = true;
        visit['i'-'a'] = true;
        visit['c'-'a'] = true;

        dfs(0, 5);
        System.out.println(answer);
    }

    private static void dfs(int alpha, int alphaCnt) {
        if (alphaCnt == k) {
            int cnt = 0;
            for (String word: words) {
                if (isReadable(word)) {
                    cnt++;
                }
            }
            answer = Math.max(answer, cnt);
            return;
        }

        for (int i = alpha + 1; i < 26; i++) {
            if (visit[i] == false) {
                visit[i] = true;
                dfs(i, alphaCnt+1);
                visit[i] = false;
            }
        }
    }

    private static boolean isReadable(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!visit[word.charAt(i) - 'a']) {
                return false;
            }
        }
        return true;
    }
}
