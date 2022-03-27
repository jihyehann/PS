#include <iostream>

using namespace std;

int n, m; // 행,열 개수
int room[51][51]; //0빈칸 1벽 2청소완료
int mv[4][2] = {{-1,0}, {0,1}, {1,0}, {0,-1}}; // 북동남서
int num = 1;
void clean(int x, int y, int dir) {
    room[x][y] = ++num;
    int nx, ny;
    for(int i=0; i<4; i++) {
        // 왼쪽 방향 회전
        dir = (dir + 3) % 4;
        nx = x + mv[dir][0];
        ny = y + mv[dir][1];
        if(nx <= 0 || nx > n || ny <= 0 || ny > m)
            continue;
        if(room[nx][ny] == 0) {
            clean(nx, ny, dir);
            return;
        }
    }

    // 네 방향 모두 청소할 수 없는 경우
    nx = x + mv[(dir+2)%4][0];
    ny = y + mv[(dir+2)%4][1];

    // 뒤쪽 방향이 벽인 경우
    if(nx <= 0 || nx > n || ny <= 0 || ny > m) return;
    if(room[nx][ny] == 1) return;

    // 후진
    else clean(nx, ny, dir);
}

int count() {
    int cnt = 0;
    for(int i=1;i<n;i++){
        for(int j=1;j<m;j++){
            if(room[i][j] >= 2) cnt++;
        }
    }
    return cnt;
}

int main() {
    cin >> n >> m;

    int x, y, d;
    cin >> x >> y >> d;

    for(int i=0; i<n; i++)
        for(int j=0; j<m; j++)
            cin >> room[i][j];
    clean(x, y, d);

    cout << count() << endl;
}
