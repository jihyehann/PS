#include <vector>
#include <algorithm>
#include <climits>
using namespace std;

vector<vector<int>> arr(201, vector<int>(201, INT_MAX));

int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
    for(vector<int> f : fares) {
        arr[f[0]][f[1]] = f[2];
        arr[f[1]][f[0]] = f[2];
    }

    // 플로이드 와샬 알고리즘
    for(int k=1; k<=n; k++) {
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                // i->k->j 경로가 없는 경우
                if (arr[i][k] == INT_MAX || arr[k][j] == INT_MAX) continue;
                arr[i][j] = min(arr[i][j], arr[i][k]+arr[k][j]);
            }
        }
    }

    // 최단 거리 구하기
    int answer = min(arr[s][a]+arr[a][b], min(arr[s][b]+arr[b][a], arr[s][a]+arr[s][b]));
    for(int i=1; i<=n; i++) {
        if(i == s || i == a || i == b) continue;
        answer = min(answer, arr[s][i]+arr[i][a]+arr[i][b]);
    }

    return answer;
}
