class Solution {
    boolean[] visit;
    int[][] computers;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visit = new boolean[n];
        this.computers = computers;
        for (int i=0; i<n; i++) {
            if (!visit[i]) {
                dfs(i, n);
                answer++;
            }
        }
        return answer;
    }

    private void dfs(int x, int n) {
        visit[x] = true;
        for (int i=0; i<n; i++) {
            if (visit[i] || computers[x][i] == 0) continue;
            dfs(i, n);
        }
    }
}