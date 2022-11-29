#include <iostream>
using namespace std;
int w, h, building[102][102] = {0};
bool visit[102][102] = {false};

int dir[8][2] ={
        // 상하좌우
        {-1,0}, {1,0}, {0,-1}, {0,1},
        // 왼쪽 대각선 - 짝수
        {1,-1}, {-1,-1},
        // 오른쪽 대각선 - 홀수
        {1,1}, {-1,1}
};

int getZeroSize(int x, int y);

int getZeroCnt(int &x, int &y) {
    int cnt = 0;
    if (building[x][y] == 1) cnt++;
    else if (!visit[x][y]){
        visit[x][y] = true;
        int tmp = getZeroSize(x, y);
        if (tmp == -1) return -1;
        cnt += tmp;
    }
    return cnt;
}

int getZeroSize(int x, int y) {
    bool isClosed = true;
    int cnt = 0;
    for (int i=0; i<4; i++) {
        int nx = x+dir[i][0];
        int ny = y+dir[i][1];
        if (nx <= 0 || nx > h || ny <= 0 || ny > w) {
            isClosed = false;
            continue;
        }
        int tmp = getZeroCnt(nx, ny);
        if (tmp == -1) {
            isClosed = false;
            continue;
        }
        cnt += tmp;
    }
    if (x%2) { // 홀수 줄
        for(int i=6; i<8; i++) {
            int nx = x+dir[i][0];
            int ny = y+dir[i][1];
            if (nx <= 0 || nx > h || ny <= 0 || ny > w) {
                isClosed = false;
                continue;
            }
            int tmp = getZeroCnt(nx, ny);
            if (tmp == -1) {
                isClosed = false;
                continue;
            }
            cnt += tmp;
        }
    } else { // 짝수 줄
        for(int i=4; i<6; i++) {
            int nx = x+dir[i][0];
            int ny = y+dir[i][1];
            if (nx <= 0 || nx > h || ny <= 0 || ny > w) {
                isClosed = false;
                continue;
            }
            int tmp = getZeroCnt(nx, ny);
            if (tmp == -1) {
                isClosed = false;
                continue;
            }
            cnt += tmp;
        }
    }
    if (isClosed) return cnt;
    else return -1;
}

int check(int x, int y) {
    int cnt = 0;
    for(int i=0; i<4; i++) {
        int nx = x+dir[i][0];
        int ny = y+dir[i][1];
        if (building[nx][ny] == 0) cnt++;
    }
    if (x%2) { // 홀수 줄
        for(int i=6; i<8; i++) {
            int nx = x+dir[i][0];
            int ny = y+dir[i][1];
            if (building[nx][ny] == 0) cnt++;
        }
    } else { // 짝수 줄
        for(int i=4; i<6; i++) {
            int nx = x+dir[i][0];
            int ny = y+dir[i][1];
            if (building[nx][ny] == 0) cnt++;
        }
    }
    return cnt;
}

int main() {
    ios::sync_with_stdio(false);

    cin >> w >> h;
    for (int i=1; i<=h; i++) {
        for (int j=1; j<=w; j++) {
            cin >> building[i][j];
        }
    }
    int answer = 0;
    for (int i=1; i<=h; i++) {
        for (int j=1; j<=w; j++) {
            if (building[i][j] == 1) {
                answer += check(i, j);
            }
            else if (!visit[i][j]) {
                visit[i][j] = true;
                int cnt = getZeroSize(i,j);
                if (cnt != -1) {
                    answer -= cnt;
                }
            }
        }
    }

    cout << answer << "\n";
}