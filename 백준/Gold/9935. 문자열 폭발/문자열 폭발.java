import java.io.*;
import java.util.Stack;

public class Main {
    static final String END = "FRULA";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String pattern = br.readLine();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == pattern.charAt(pattern.length()-1)) {
                boolean find = true;
                for (int j = 2; j <= pattern.length(); j++) {
                    if (stack.size()+1 < pattern.length() || pattern.charAt(pattern.length()-j) != stack.get(stack.size()-j+1)) {
                        find = false;
                        break;
                    }
                }
                if (find) {
                    for (int j = 0; j < pattern.length()-1; j++) {
                        stack.pop();
                    }
                } else {
                    stack.add(input.charAt(i));
                }
            } else {
                stack.add(input.charAt(i));
            }
        }

        StringBuilder answer = new StringBuilder();
        if (stack.isEmpty()) {
            System.out.println(END);
        } else {
            while (!stack.isEmpty()) {
                answer.append(stack.pop());
            }
            System.out.println(answer.reverse());
        }
    }
}
