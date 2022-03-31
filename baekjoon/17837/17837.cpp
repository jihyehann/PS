#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n, k;
int color[13][13]; // 0w 1r 2b
pair<int, pair<int,int>> piece[11]; // 방향 행 열
vector<int> board[13][13]; // 번호
int mv[5][2]={{0,0}, {0,1}, {0, -1}, {-1,0}, {1,0}}; // x 우좌상하


int play() {
    int cnt = 0;
    while(1) {
        cnt++;
        if(cnt > 1000)
            return -1;
        for(int i=0; i<k; i++) {
            int x = piece[i].second.first;
            int y = piece[i].second.second;
            int d = piece[i].first;
            int nx = x + mv[d][0];
            int ny = y + mv[d][1];

            if(color[nx][ny] == 2 || (nx <= 0 || nx > n || ny <= 0 || ny > n)) { // blue or out
                if(d % 2 == 0) d--;
                else d++;
                piece[i].first = d;
                nx = x + mv[d][0];
                ny = y + mv[d][1];
            }

            if(color[nx][ny] == 2 || (nx <= 0 || nx > n || ny <= 0 || ny > n)){
                continue;
            }

            else if(color[nx][ny] == 0) { // white
                vector<int>::iterator iter = find(board[x][y].begin(), board[x][y].end(), i);
                vector<int>::iterator iter2 = iter;
                for(;iter != board[x][y].end(); iter++) {
                    piece[*iter].second = {nx, ny};
                    board[nx][ny].push_back(*iter);
                }
                board[x][y].erase(iter2, board[x][y].end());
            }
            else { // red
                vector<int>::iterator iter = find(board[x][y].begin(), board[x][y].end(), i);
                for(vector<int>::iterator iter2 = board[x][y].end()-1; iter2 != iter; iter2--) {
                    piece[*iter2].second = {nx, ny};
                    board[nx][ny].push_back(*iter2);
                }
                piece[*iter].second = {nx, ny};
                board[nx][ny].push_back(*iter);
                board[x][y].erase(iter, board[x][y].end());
            }

            if(board[nx][ny].size() > 3)
                return cnt;
        }
    }
}

int main() {
    cin >> n >> k;

    for(int i=1; i<=n; i++)
        for(int j=1; j<=n; j++)
            cin >> color[i][j];

    for(int i=0; i<k; i++){
        int x, y, d;
        cin >> x >> y >> d;
        piece[i] = {d, {x, y}};
        board[x][y].push_back(i);
    }

    cout << play() << endl;
}
