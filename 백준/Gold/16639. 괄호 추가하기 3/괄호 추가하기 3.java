import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static String exp;
    static int[][] max;
    static int[][] min;
    static int numCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        exp = br.readLine();
        numCnt = N/2+1;
        max = new int[numCnt][numCnt];
        min = new int[numCnt][numCnt];

        for (int i = 0; i < numCnt; i++) {
            max[i][i] = min[i][i] = exp.charAt(i * 2) - '0';
        }
        // 좁은 범위부터 구하기
        for (int cnt = 1; cnt < numCnt; cnt++) {
            for (int i = 0; i < numCnt - cnt; i++) {
                solve(i, i+cnt);
            }
        }
        System.out.println(max[0][numCnt-1]);
    }

    static void solve(int s, int d) {
        List<Integer> tmp = new ArrayList<>();
        for (int i = s; i < d; i++) {
            char op = exp.charAt(i*2+1);
            tmp.add(calc(max[s][i], max[i+1][d], op));
            tmp.add(calc(max[s][i], min[i+1][d], op));
            tmp.add(calc(min[s][i], max[i+1][d], op));
            tmp.add(calc(min[s][i], min[i+1][d], op));
        }
        Collections.sort(tmp);
        max[s][d] = tmp.get(tmp.size()-1);
        min[s][d] = tmp.get(0);
    }

    static int calc(int num1, int num2, char op) {
        if (op == '+') return num1+num2;
        else if (op == '-') return num1-num2;
        else return num1*num2;
    }
}
