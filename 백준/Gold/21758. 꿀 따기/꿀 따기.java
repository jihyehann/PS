import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> honey = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        br.close();
        
        // 누적합
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++) {
            sum[i] += (sum[i-1] + honey.get(i-1));
        }

        int answer = 0;
        for (int i=2; i<n; i++) {
            // 벌(1) 벌(i) 벌통(n)
            answer = Math.max(answer, sum[n]*2 - honey.get(0) - honey.get(i-1) - sum[i]);

            // 벌통(1) 벌(i) 벌(n)
            answer = Math.max(answer, sum[n-1]-honey.get(i-1) + sum[i-1]);

            // 벌(1) 벌통(i) 벌(n)
            answer = Math.max(answer, sum[i]-honey.get(0) + sum[n-1]-sum[i-1]);
        }
        System.out.println(answer);
    }
}
