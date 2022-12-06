#include <iostream>
#include <algorithm>
using namespace std;

int board[20][20]; // 1흑 2백
bool visit[20][20][4] = {false};

void input() {
    ios::sync_with_stdio(false);
    for (int i=0; i<19; i++) {
        for (int j=0; j<19; j++) {
            cin >> board[i][j];
        }
    }
}

int mv[4][2] = {{0,1},{1,1},{1,0},{-1,1}};
int getCnt(int x, int y, int cnt, int dir) {
    visit[x][y][dir] = true;
    int nx = x + mv[dir][0];
    int ny = y + mv[dir][1];
    if (nx >= 19 || ny >= 19) return cnt;
    if (board[nx][ny] != board[x][y]) return cnt;
    return getCnt(nx, ny, cnt+1, dir);
}

int main() {
    input();

    for (int j=0; j<19; j++) {
        for (int i=0; i<19; i++) {
            if (board[i][j] == 0) continue;
            for (int k=0; k<4; k++) {
                if (!visit[i][j][k] && getCnt(i,j,1,k) == 5) {
                    cout << board[i][j] << endl;
                    cout << i+1 << " " << j+1 << endl;
                    return 0;
                }
            }

        }
    }

    cout << 0 << endl;
}