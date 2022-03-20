#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int n, m, cctvCnt = 0, ans = 123456789;
int office[10][10];
vector<pair<int, int>> cctv;
int mv[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우

// 사각지대 개수 계산
int count(int office[10][10]) {
    int cnt = 0;
    for(int i=0; i<n; i++) {
        for (int j = 0; j < m; j++) {
            if (office[i][j] == 0)
                cnt++;
        }
    }
    return cnt;
}

void copyArray(int (&to)[10][10], int from[10][10]) {
    for(int i=0; i<n; i++)
        for (int j = 0; j < m; j++)
            to[i][j] = from[i][j];
}

void go(int x, int y, int dir, int (&visit)[10][10]) {
    int nx = x;
    int ny = y;
    while(1) {
        nx += mv[dir][0];
        ny += mv[dir][1];
        if(nx < 0 || nx >= n || ny < 0 || ny >= m) break;
        if(visit[nx][ny] == 6) break;
        if(visit[nx][ny] != 0) continue;
        visit[nx][ny] = -1;
    }
}

void supervise(int index, int office[10][10]) {
    if(index == cctvCnt){
        int cnt = count(office);
        ans = min(ans, cnt);
        return;
    }

    int visit[10][10];
    int x = cctv[index].first;
    int y = cctv[index].second;
    int type = office[x][y];

    switch(type) {
        case 1:
            for(int i=0; i<4; i++) {
                copyArray(visit, office);
                go(x, y, i, visit);
                supervise(index+1, visit);
            }
            break;
        case 2:
            // 01, 23
            for(int i=0; i<=2; i+=2) {
                copyArray(visit, office);
                go(x, y, i, visit);
                go(x, y, i+1, visit);
                supervise(index+1, visit);
            }
            break;
        case 3:
            //02, 03, 12, 13
            for(int i=0; i<2; i++) {
                copyArray(visit, office);
                go(x, y, i, visit);
                go(x, y, 2, visit);
                supervise(index+1, visit);

                copyArray(visit, office);
                go(x, y, i, visit);
                go(x, y, 3, visit);
                supervise(index+1, visit);
            }
            break;
        case 4:
            for(int i=0; i<4; i++) {
                copyArray(visit, office);
                go(x, y, (i+1)%4, visit);
                go(x, y, (i+2)%4, visit);
                go(x, y, (i+3)%4, visit);
                supervise(index+1, visit);
            }
            break;
        case 5:
            copyArray(visit, office);
            for(int i=0; i<4; i++)
                go(x, y, i, visit);
            supervise(index+1, visit);
            break;

    }
}

int main() {
    cin >> n >> m;
    for(int i=0; i<n; i++)
        for(int j=0; j<m; j++) {
            cin >> office[i][j];
            if(office[i][j] != 0 && office[i][j] != 6){
                cctv.push_back({i, j});
                cctvCnt++;
            }
        }

    supervise(0, office);
    cout << ans <<endl;
    return 0;
}
