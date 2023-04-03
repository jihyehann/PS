class Solution {
    int[][] dungeons;
    int answer = 0;
    public int solution(int k, int[][] dungeons) {
        this.dungeons = dungeons;
        boolean[] visit = new boolean[dungeons.length];
        dfs(k, visit, 0);
        return answer;
    }
    
    void dfs(int k, boolean[] visit, int cnt) {
        answer = Math.max(answer, cnt);
        for (int i=0; i<visit.length; i++) {
            if (visit[i] || k < dungeons[i][0]) continue;
            visit[i] = true;
            dfs(k-dungeons[i][1], visit, cnt+1);
            visit[i] = false;
        }
    }
}