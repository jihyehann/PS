import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Integer> diff = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        int prev = Integer.parseInt(st.nextToken());
        for (int i = 1; i < n; i++) {
            int now = Integer.parseInt(st.nextToken());
            diff.add(now-prev);
            prev = now;
        }
        Collections.sort(diff);
        long answer = 0;
        for (int i = 0; i < n - k; i++) {
            answer += diff.get(i);
        }
        System.out.println(answer);

    }
}
