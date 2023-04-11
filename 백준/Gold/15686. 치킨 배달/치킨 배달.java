import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.awt.Point;

public class Main {
    static boolean[] open = new boolean[13];
    static List<Point> house = new ArrayList<>();
    static List<Point> chicken = new ArrayList<>();
    static int N, M, answer = 5000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        for (int i=0; i<N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                if (input[j].equals("1")) {
                    house.add(new Point(i,j));
                } else if (input[j].equals("2")) {
                    chicken.add(new Point(i,j));
                }
            }
        }
        open[0] = true;
        dfs(0, 1);
        open[0] = false;
        dfs(0, 0);
        System.out.println(answer);
    }

    static int getDist() {
        int answer = 0;
        for (int i = 0; i < house.size(); i++) {
            Point h = house.get(i);
            int dist = 100;
            for (int j = 0; j < chicken.size(); j++) {
                if (open[j]) {
                    dist = Math.min(dist, Math.abs(h.x - chicken.get(j).x) +  Math.abs(h.y - chicken.get(j).y));
                }
            }
            answer += dist;
        }
        return answer;
    }

    static void dfs(int now, int cnt) {
        if (cnt == M) {
            answer = Math.min(answer, getDist());
        }
        for (int i=now+1; i<chicken.size(); i++) {
            open[i] = true;
            dfs(i, cnt+1);
            open[i] = false;
        }
    }
}
