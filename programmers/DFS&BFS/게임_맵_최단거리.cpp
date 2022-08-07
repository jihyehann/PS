#include <vector>
#include <queue>
#include <tuple>
using namespace std;

int mv[4][2] = {{0,1}, {1,0}, {0,-1},{-1,0}};

int solution(vector<vector<int>> maps) {
    bool visit[101][101] = {false};
    int n, m;
    n = maps.size();
    m = maps[0].size();

    visit[0][0] = true;
    queue<tuple<int,int,int>> q; // x,y,cnt
    q.emplace(0,0,1);
    while(!q.empty()) {
        int x = get<0>(q.front());
        int y = get<1>(q.front());
        int cnt = get<2>(q.front());
        q.pop();

        for(int i=0; i<4; i++) {
            int nx = x + mv[i][0];
            int ny = y + mv[i][1];
            if(nx == n-1 && ny == m-1) {
                return cnt+1;
            }
            if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if(visit[nx][ny] || maps[nx][ny] == 0) continue;
            visit[nx][ny] = true;
            q.emplace(nx, ny, cnt+1);
        }
    }

    return -1;
}
