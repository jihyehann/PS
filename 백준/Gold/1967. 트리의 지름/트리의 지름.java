import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static List<List<Node>> tree = new ArrayList<>();
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < n-1; i++) {
            String[] input = br.readLine().split(" ");
            int parent = Integer.parseInt(input[0]);
            int child = Integer.parseInt(input[1]);
            int cost = Integer.parseInt(input[2]);
            tree.get(parent).add(new Node(child, cost));
        }

        System.out.println(Math.max(solve(1, 0), answer));
    }

    private static int solve(int node, int cost) {
        if (tree.get(node).isEmpty()) { // 리프 노드인 경우
            return cost;
        }
        int ret = 0;
        List<Integer> childCosts = new ArrayList<>();
        for (Node child : tree.get(node)) {
            int childCost = solve(child.num, cost+child.cost);
            childCosts.add(childCost - cost);
            ret = Math.max(ret, childCost);
        }

        if (childCosts.size() > 2) {
            childCosts.sort(Collections.reverseOrder());
            answer = Math.max(answer, childCosts.get(0) + childCosts.get(1));
        } else if(childCosts.size() == 2) {
            answer = Math.max(answer, childCosts.get(0) + childCosts.get(1));
        } else if (childCosts.size() == 1) {
            answer = Math.max(answer, childCosts.get(0));
        }
        return ret;
    }

    static class Node {
        int num;
        int cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}
