#include <string>
#include <vector>
#include <queue>
#include <tuple>
using namespace std;

int map[101][101] = {0};

int solution(vector<vector<int>> rectangle, int characterX, int characterY, int itemX, int itemY) {
    int answer = 0;
    
    // 테두리 1로 설정
    for(auto rec : rectangle) {
        int x1 = rec[0], y1 = rec[1], x2 = rec[2], y2 = rec[3];
        for(int y=y1*2; y<=y2*2; y++) {
            for(int x=x1*2; x<=x2*2; x++) {
                // 테두리인 경우
                if(x==x1*2 || x==x2*2 || y == y1*2 || y == y2*2) {
                    if(map[y][x] != 2) map[y][x] = 1;
                }
                // 내부인 경우
                else map[y][x] = 2;
            }
        }
    }
    
    // 최단 경로 구하기 (BFS)
    queue<tuple<int,int,int>> q;
    q.emplace(characterX*2, characterY*2, 0);
    bool visit[101][101] = {false};
    int mv[4][2] = {{0,1}, {0,-1}, {-1,0}, {1,0}};
    visit[characterY*2][characterX*2] = true;
    while(!q.empty()) {
        int x = get<0>(q.front());
        int y = get<1>(q.front());
        int cnt = get<2>(q.front());
        q.pop();
        for(int i=0; i<4; i++) {
            int nx = x + mv[i][1];
            int ny = y + mv[i][0];
            if(nx < 0 || nx > 100 || ny < 0 || ny > 100) continue;
            if(map[ny][nx] != 1) continue;
            if(nx == itemX*2 && ny == itemY*2) return (cnt+1)/2;
            if(visit[ny][nx]) continue;
            visit[ny][nx] = true;
            q.emplace(nx, ny, cnt+1);
        }
    }
}
