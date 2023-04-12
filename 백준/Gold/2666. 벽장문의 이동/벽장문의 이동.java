import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class Main {
    static int N, answer = Integer.MAX_VALUE;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = parseInt(br.readLine());
        String[] input =  br.readLine().split(" ");
        int n = parseInt(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = parseInt(br.readLine());
        }

        int a = parseInt(input[0]);
        int b = parseInt(input[1]);
        dfs(a, b, 0, 0);
        System.out.println(answer);
    }

    static void dfs(int a, int b, int cnt, int index) {
        if (index == arr.length) {
            answer = Math.min(answer, cnt);
            return;
        }
        int aCnt = Math.abs(arr[index]-a);
        int bCnt = Math.abs(arr[index]-b);
        dfs(arr[index], b, cnt+aCnt, index+1);
        dfs(a, arr[index], cnt+bCnt, index+1);
    }
}
