#include <iostream>
#include <vector>
#include <cstring>
#include <algorithm>
using namespace std;

struct sharkInfo {
    int x, y, speed, d, size;
};

vector<int> board[102][102];
vector<sharkInfo> shark;
bool isDead[10010];
int r,c,m;
int dir[4][2] = {{-1,0}, {1,0}, {0,1}, {0,-1}}; // 위 아래 오른쪽 왼쪽

// 상어 이동
void sharkMove() {
    // m 마리 상어 이동
    for(int i=0; i<m; i++){

        // 상어가 죽은 경우
        if(isDead[i]) continue;

        int s = shark[i].speed; // 속력
        int &d = shark[i].d; // 방향
        int x = shark[i].x;
        int y = shark[i].y;

        // 속력만큼 이동
        int nx = x, ny = y;
        for(int j=0; j<s; j++) {
            nx += dir[d-1][0];
            ny += dir[d-1][1];

            // 경계를 넘는 경우 - 반대 방향으로 이동
            if(nx < 1 || nx > r || ny < 1 || ny > c) {
                nx -= dir[d-1][0];
                ny -= dir[d-1][1];

                if(d % 2 == 1) d++;
                else d--;
                nx += dir[d-1][0];
                ny += dir[d-1][1];
            }
        }

        // 이동
        board[x][y].erase(remove(board[x][y].begin(), board[x][y].end(), i), board[x][y].end());
        board[nx][ny].push_back(i);
        shark[i].x = nx;
        shark[i].y = ny;
    }
}

// 칸이 겹치는 상어 잡아먹기
void eatShark() {
    for(int i=0; i<m; i++) {
        if(isDead[i]) continue;

        int x = shark[i].x;
        int y = shark[i].y;

        if(board[x][y].size() > 1) {
            int biggestNum = board[x][y][0];
            int cnt = board[x][y].size();
            for(int j=1; j<cnt; j++) {
                int num = board[x][y][j];
                if(shark[num].size > shark[biggestNum].size) {
                    isDead[biggestNum] = true;
                    biggestNum = num;
                } else {
                    isDead[num] = true;
                }
            }
            board[x][y].clear();
            board[x][y].push_back(biggestNum);
        }
    }
}

int main() {
    cin >> r >> c >> m;
    memset(isDead, false, sizeof(isDead));
    for(int i=0; i<m; i++) {
        int x, y, s, d, z;
        cin >> x >> y >> s >> d >> z;
        board[x][y].push_back(i);
        if(d < 3) s = s % (2*(r-1));
        else s = s % (2*(c-1));
        shark.push_back({x, y, s, d, z});
    }

    int sum = 0;
    for(int i=1; i<=c; i++) {
        for(int j=1; j<=r; j++) {
            if(!board[j][i].empty()) {
                int num = board[j][i][0];
                sum += shark[num].size;
                isDead[num] = true;
                board[j][i].clear();
                break;
            }
        }
        sharkMove();    // 상어 이동
        eatShark();     // 칸이 겹치는 상어 잡아먹기
    }
    cout << sum << endl;
}
