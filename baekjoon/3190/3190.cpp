#include <iostream>
#include <cstring>
#include <vector>
#include <queue>
#include <cstdio>
using namespace std;
int n, k;
int board[101][101]; // 0빈칸 1뱀 2사과
int mv[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
vector<pair<int, int>> rotateInfo; // 방향 변환 정보, 좌0 우1
queue<pair<int, int>> snake;

int main() {
    cin >> n >> k;
    memset(board, 0, sizeof(board));

    //사과 위치 입력
    for(int i=0; i<k; i++) {
        int x, y;
        cin >> x >> y;
        board[x][y] = 2; // 사과 표시
    }
    int l;
    cin >> l;
    // 방향 변환 입력
    for(int i=0; i<l; i++) {
        int sec;
        char r;
        scanf("%d %c", &sec, &r);
        if(r == 'L') rotateInfo.push_back({sec, 0}); // 좌0
        else rotateInfo.push_back({sec, 1}); // 우1
    }

    int mvDir = 0, rotateIndex = 0;
    int x = 1, y = 1;
    int ans = 0;
    board[x][y] = 1;
    snake.push({x, y});

    while(1) {
        ans++;

        x += mv[mvDir][0];
        y += mv[mvDir][1];

        if(x <= 0 || x > n || y <= 0 || y > n) break; // 벽에 닿음
        if(board[x][y] == 1) break; // 몸에 닿음

        if(board[x][y] != 2) { // 사과가 아닌 경우 꼬리 칸 비워줌
            pair<int, int> tail = snake.front();
            snake.pop();
            board[tail.first][tail.second] = 0;
        }
        board[x][y] = 1;
        snake.push({x, y});

        // 방향 전환
        if(rotateInfo[rotateIndex].first == ans) {
            if(rotateInfo[rotateIndex].second == 0) { // 좌회전
                switch (mvDir) {
                    case 0: mvDir = 3; break;
                    case 1: mvDir = 0; break;
                    case 2: mvDir = 1; break;
                    case 3: mvDir = 2; break;
                }
            }
            else { // 우회전
                mvDir = (mvDir + 1) % 4;
            }
            rotateIndex++;
        }
    }
    cout << ans <<endl;
}
