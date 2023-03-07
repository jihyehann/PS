import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n;
    static int m;
    static int[] course;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        course = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 이분탐색
        long left = 1;
        long right = 1_000_000_000;
        long answer = right;
        while (left <= right) {
            long mid = (left + right)/2;
            if (isPossible(mid)) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean isPossible(long value) {
        int b = m; // 블루레이 개수
        long sum = 0;
        for (int i = 0; i < n; i++) {
            if (course[i] > value) {
                return false;
            }
            sum += course[i];
            if (sum > value) {
                if (b < 1) {
                    return false;
                }
                b--;
                sum = course[i];
            }
        }
        return sum <= 0 || b >= 1;
    }
}
