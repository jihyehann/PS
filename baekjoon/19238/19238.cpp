#include <iostream>
#include <queue>
#include <cstring>
#include <algorithm>
using namespace std;

int n, m, f;
int taxi[2]; //택시 위치
int map[22][22];
int mv[4][2] = {{1,0}, {-1,0}, {0,1}, {0,-1}};
bool done[401];
pair<pair<int, int>, pair<int, int>> ps[401]; // 출발위치, 도착위치

int calcPath(int sx, int sy, int dx, int dy) {

    if(sx == dx && sy == dy) return 0;

    bool visit[22][22];
    memset(visit, false, sizeof(visit));
    queue<pair<pair<int,int>, int>> q;
    q.push({{sx, sy}, 0});
    visit[sx][sy] = true;

    while(!q.empty()) {
        int x = q.front().first.first;
        int y = q.front().first.second;
        int cnt = q.front().second;
        q.pop();

        for(int i=0; i<4; i++) {
            int nx = x + mv[i][0];
            int ny = y + mv[i][1];
            if(nx <= 0 || nx > n || ny <= 0 || ny > n) continue;
            if(map[nx][ny] == 1 || visit[nx][ny] == true) continue;
            if(nx == dx && ny == dy)
                return (cnt + 1);
            visit[nx][ny] = true;
            q.push({{nx, ny}, cnt+1});

        }
    }
    return -1;

}

int drive() {
    int cnt = 0;
    while(1) {
        cnt++;
        int psIndex, srcPath = 123456789;

        // 다음 손님 정하기
        for(int i=0; i<m; i++) {
            if(done[i] == true) continue;
            int temp = calcPath(taxi[0], taxi[1], ps[i].first.first, ps[i].first.second);
            if(temp == -1) return -1;
            if(temp < srcPath) {
                srcPath = temp;
                psIndex = i;
            }
            else if(temp == srcPath) {
                if(ps[i].first.first < ps[psIndex].first.first) {
                    srcPath = temp;
                    psIndex = i;
                }
                else if(ps[i].first.first == ps[psIndex].first.first) {
                    if(ps[i].first.second < ps[psIndex].first.second) {
                        srcPath = temp;
                        psIndex = i;
                    }
                }
            }
        }

        // 이동
        int dstPath = calcPath(ps[psIndex].first.first, ps[psIndex].first.second, ps[psIndex].second.first, ps[psIndex].second.second);

        if(dstPath == -1) return -1;
        
        int curF = f - srcPath - dstPath;
        if(curF < 0) return -1;
        curF += dstPath * 2;
        f = curF;

        taxi[0] = ps[psIndex].second.first;
        taxi[1] = ps[psIndex].second.second;
        done[psIndex] = true;

        if(cnt == m) return f;

    }
}


int main() {
    memset(done, false, sizeof(done));
    cin >> n >> m >> f;
    for(int i=1; i<=n; i++)
        for(int j=1; j<=n; j++)
            cin >> map[i][j];

    cin >> taxi[0] >> taxi[1];
    for(int i=0; i<m; i++) {
        int sx, sy, dx, dy;
        cin >> sx >> sy >> dx >> dy;
        ps[i] = {{sx, sy}, {dx, dy}};
    }

    cout << drive() << endl;
}
