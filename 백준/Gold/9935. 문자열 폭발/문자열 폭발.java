import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        char[] pattern = br.readLine().toCharArray();
        int inputLength = input.length;
        int top = 0;
        int index = 0;
        while (index < inputLength) {
            input[top++] = input[index++];
            if (top >= pattern.length) {
                boolean find = true;
                for (int i = 1; i <= pattern.length; i++) {
                    if (input[top-i] != pattern[pattern.length-i]) {
                        find = false;
                        break;
                    }
                }
                if (find) {
                    top -= pattern.length;
                }
            }
        }

        if (top == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(new String(input, 0, top));
        }

    }
}
