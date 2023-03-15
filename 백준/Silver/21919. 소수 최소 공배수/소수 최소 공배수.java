import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static final int MAX = 1_000_000;
    static boolean[] prime = new boolean[MAX+1];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        getPrime();
        long answer = 1;
        for (int i = 0; i < n; i++) {
            if (prime[nums[i]]) {
                answer = answer * nums[i] / gcd(nums[i], answer);
            }
        }
        if (answer == 1) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static void getPrime() {
        for (int i = 2; i <= MAX; i++) {
            prime[i] = true;
        }
        for (int i = 2; i <= MAX; i++) {
            if (!prime[i]) continue;
            for (int j = 2*i; j <= MAX; j+=i) {
                prime[j] = false;
            }
        }
    }

    private static long gcd(long n1, long n2) {
        if (n2 == 0) return n1;
        else return gcd(n2, n1 % n2);
    }
}
