import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Main {
    static Belt[] belt;

    static List<Integer> robots = new ArrayList<>();
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = parseInt(input[0]);
        K = parseInt(input[1]);
        belt = new Belt[2*N];
        input = br.readLine().split(" ");
        for (int i = 0; i < 2 * N; i++) {
            belt[i] = new Belt(parseInt(input[i]));
        }
        int answer = 0;
        int pos = 0;
        while (countZero() < K) {
            answer++;
            pos = (pos+2*N-1) % (2*N); // 올리는 위치, 한 칸 이동
            move(pos);
        }
        System.out.println(answer);
    }

    private static void move(int pos) {
        // 로봇 이동
        int rmPos = (pos+N-1)%(2*N); // 내리는 위치
        for (int i = 0; i < robots.size(); i++) {
            int now = robots.get(i);
            int next = (now+1)%(2*N);
            if (now == rmPos) {
                belt[now].robot = false;
                robots.remove(i);
                i--;
            }
            else if (belt[next].number != 0 && !belt[next].robot) {
                belt[next].number--;
                belt[now].robot = false;
                if (next == rmPos) {
                    robots.remove(i);
                    i--;
                } else {
                    belt[next].robot = true;
                    robots.set(i, next);
                }
            }
        }

        // 로봇 올리기
        if (belt[pos].number != 0 && !belt[pos].robot) {
            robots.add(pos);
            belt[pos].number--;
            belt[pos].robot = true;
        }
    }

    private static int countZero() {
        int cnt = 0;
        for (int i=0; i<2*N; i++) {
            if (belt[i].number == 0) cnt++;
        }
        return cnt;
    }

    static class Belt {
        int number;
        boolean robot;

        public Belt(int number) {
            this.number = number;
            this.robot = false;
        }
    }
}
