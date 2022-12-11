import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String src = br.readLine();
        StringBuilder dest = new StringBuilder(br.readLine());

        while (src.length() < dest.length()) {
            if (dest.charAt(dest.length()-1) == 'A') {
                dest.deleteCharAt(dest.length()-1);
            }
            else if (dest.charAt(dest.length()-1) == 'B') {
                dest.deleteCharAt(dest.length()-1);
                dest.reverse();
            }
        }

        if (src.equals(dest.toString())) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

}
