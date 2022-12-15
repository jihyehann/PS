import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    ArrayList<Integer>[] nodes;
    int answer = 100;


    public int solution(int n, int[][] wires) {
        nodes = new ArrayList[n+1];
        for (int i = 1; i < n + 1; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int i=0; i<n-1; i++) {
            nodes[wires[i][0]].add(wires[i][1]);
            nodes[wires[i][1]].add(wires[i][0]);
        }

        boolean[] visit = new boolean[n + 1];
        Arrays.fill(visit, false);
        getDiff(n, 1, visit);

        return answer;
    }

    int getDiff(int n, int x, boolean[] visit) {
        int cnt = 1;
        visit[x] = true;
        for (Integer node : nodes[x]) {
            if (visit[node]) continue;
            int tmp = getDiff(n, node, visit);
            answer = Math.min(answer, Math.abs(tmp-(n-tmp)));
            cnt += tmp;
        }
        return cnt;
    }
}
