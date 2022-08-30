#include <string>
#include <vector>
using namespace std;
int n, mv[4][2] = {{1,0},{-1,0},{0,1},{0,-1}};
bool visitTable[51][51] = {false};
vector<vector<pair<int, int>>> blocks;

// table에서 조각 얻기 (block)
void getBlock(int x, int y, vector<vector<int>> &table, vector<pair<int, int>> &block) {
    visitTable[x][y] = true;
    block.push_back({x,y});
    for(int i=0; i<4; i++) {
        int nx = x + mv[i][0];
        int ny = y + mv[i][1];
        if(nx < 0 || nx >= n || ny < 0 || ny >=n) continue;
        if(!table[nx][ny]) continue;
        if(visitTable[nx][ny]) continue;
        getBlock(nx, ny, table, block);
    }
}

// 퍼즐 맞추기
// 딱 맞게 들어가는 공간이 있으면 true
bool putPuzzle(vector<vector<int>> &game_board, vector<pair<int, int>> &block) {
    for(int i=0; i<n; i++) {
        for(int j=0; j<n; j++) {
            if(game_board[i][j] == 0) {
                bool fit = true;
                for(auto p : block) {
                    int x = i+p.first;
                    int y = j+p.second;
                    if(x < 0 || x >= n || y < 0 || y >=n) {
                        fit = false; break;
                    }
                    if(game_board[x][y] != 0) {
                        fit = false; break;
                    }
                }
                if(fit) {
                    // 1로 채우기
                    for(auto p : block) {
                        int x = i+p.first;
                        int y = j+p.second;
                        game_board[x][y] = 1;
                    }

                    // 빈 공간이 있는지 검사
                    for(auto p : block) {
                        int x = i+p.first;
                        int y = j+p.second;
                        for(int i=0; i<4; i++) {
                            int nx = x + mv[i][0];
                            int ny = y + mv[i][1];
                            if(nx < 0 || nx >= n || ny < 0 || ny >=n) continue;
                            if(game_board[nx][ny] == 0) {
                                fit = false; break;
                            }
                        }
                        if(fit == false) break;
                    }
                    if(fit) return true;

                    // 빈 공간이 있는 경우, 다시 0으로 채우기
                    for(auto p : block) {
                        int x = i+p.first;
                        int y = j+p.second;
                        game_board[x][y] = 0;
                    }
                }
            }
        }
    }
    
    return false;
}


// 시계 방향 90도 회전
// x,y -> y,-x
void rotate(vector<vector<pair<int, int>>> &blocks) {
    for(auto &block : blocks) {
        for(auto &p : block) {
            int temp = p.first;
            p.first = p.second;
            p.second = -temp;
        }
    }
}

int solution(vector<vector<int>> game_board, vector<vector<int>> table) {
    n = table.size();

    for(int i=0; i<n; i++) {
        for(int j=0; j<n; j++) {

            // 전체 조각 얻기 (blocks)
            if(!visitTable[i][j] && table[i][j]) {
                vector<pair<int, int>> block;
                getBlock(i, j, table, block);
                if(block.size()) {
                    // 시작 위치를 0,0으로 만들기
                    for(auto &p : block) {
                        p.first -= i;
                        p.second -= j;
                    }
                    blocks.push_back(block);
                }
            }
        }
    }

    int answer = 0;

    for(int i=0; i<4; i++) {
        // 90, 180, 270도 회전
        if(i != 0) rotate(blocks);

        for(auto block = blocks.begin(); block != blocks.end(); block++) {
            if(putPuzzle(game_board, *block)) {
                answer += (*block).size();
                blocks.erase(block);
                block--;
            }
        }
    }


    return answer;
}
