import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] solutions = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int left = 0;
        int right = n-1;
        int first = 0, second = 0;
        int minSum = 2_000_000_001;
        while (left < right) {
            int sum = solutions[left] + solutions[right];
            if (Math.abs(sum) < minSum) {
                minSum = Math.abs(sum);
                first = left;
                second = right;
            }
            if (sum >= 0) {
                right--;
            } else {
                left++;
            }
        }
        System.out.println(solutions[first] + " " + solutions[second]);
    }
}
