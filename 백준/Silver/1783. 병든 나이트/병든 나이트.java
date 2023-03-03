import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        if (n == 1) {
            System.out.println(1);
        } else if (n == 2) {
            System.out.println(Math.min(4, (m+1)/2));
        } else if (m < 7) {
            System.out.println(Math.min(4, m));
        } else {
            System.out.println(m-2);
        }
    }
}
