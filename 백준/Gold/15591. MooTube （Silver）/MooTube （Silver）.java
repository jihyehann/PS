import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<List<Node>> graph = new ArrayList<>();
    static int N;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        int Q = Integer.parseInt(input[1]);

        for (int i = 0; i < N-1; i++) {
            input = br.readLine().split(" ");
            int p = Integer.parseInt(input[0]);
            int q = Integer.parseInt(input[1]);
            int r = Integer.parseInt(input[2]);
            graph.get(p).add(new Node(q, r));
            graph.get(q).add(new Node(p, r));
        }

        while (Q-- > 0) {
            input = br.readLine().split(" ");
            int k = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            answer = 0;
            boolean[] visit = new boolean[N+1];
            dfs(v, k, visit);
            System.out.println(answer);
        }
    }

    private static void dfs(int x, int min, boolean[] visit) {
        visit[x] = true;
        for (Node next : graph.get(x)) {
            if (visit[next.num] || next.usado < min) continue;
            answer++;
            dfs(next.num, min, visit);
        }
    }

    static class Node {
        int num;
        int usado;

        public Node(int num, int usado) {
            this.num = num;
            this.usado = usado;
        }
    }
}
