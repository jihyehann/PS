import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static ArrayList<Integer>[] gear = new ArrayList[4];

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(getScore());
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i=0; i<4; i++) {
            String gearInfo = br.readLine();
            gear[i] = new ArrayList<>();
            for (int j=0; j<gearInfo.length(); j++) {
                gear[i].add(gearInfo.charAt(j)-'0');
            }
        }
        // 회전 횟수
        int k = Integer.parseInt(br.readLine());
        for (int i=0; i<k; i++) {
            int[] rotation = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            rotate(rotation[0]-1, rotation[1], true, true);
        }
        br.close();
    }

    private static void rotate(int num, int dir, boolean left, boolean right) {
        if (left && num > 0 && gear[num].get(6) != gear[num-1].get(2)) {
            rotate(num-1, -dir, true, false);
        }
        if (right && num < 3 && gear[num].get(2) != gear[num+1].get(6)) {
            rotate(num+1, -dir, false, true);
        }

        if (dir == 1) { // 시계 방향
            Integer last = gear[num].get(7);
            gear[num].remove(7);
            gear[num].add(0, last);
        } else { // 반시계 방향
            Integer first = gear[num].get(0);
            gear[num].remove(0);
            gear[num].add(first);
        }
    }

    private static int getScore() {
        int score = 0;
        for (int i = 0; i < 4; i++) {
            if (gear[i].get(0) == 1) {
                score += (1<<i);
            }
        }
        return score;
    }
}
