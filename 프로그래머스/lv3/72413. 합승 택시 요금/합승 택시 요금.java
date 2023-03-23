class Solution {
    static final int INF = 20_000_000;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = INF;
        int[][] dist = new int[n+1][n+1];
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                if (i == j) continue;
                dist[i][j] = INF;
            }
        }
        for (int i=0; i<fares.length; i++) {
            dist[fares[i][0]][fares[i][1]] = fares[i][2];
            dist[fares[i][1]][fares[i][0]] = fares[i][2];
        }
        for (int k = 1; k<=n; k++) { 
            for (int i = 1; i<=n; i++) {
                for (int j = 1; j<=n; j++) {
                    if (k == i || k == j) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        
        
        for (int i = 1; i<=n; i++) {
            answer = Math.min(answer, dist[s][i]+dist[i][a]+dist[i][b]);
        }
        return answer;
    }
}