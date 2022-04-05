#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

char board[22][22];
int r,c;
int ans = 0;
int mv[4][2] = {{0,1},{0,-1},{1,0},{-1,0}};

void dfs(int x, int y, int cnt, bool visit[27]) {
    char alpha = board[x][y];
    bool curVisit[27];
    for(int i=0; i<27; i++)
        curVisit[i] = visit[i];
    curVisit[alpha-'A'] = true;

    for(int i=0; i<4; i++) {
        int nx = x + mv[i][0];
        int ny = y + mv[i][1];
        if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

        char next = board[nx][ny];
        if(curVisit[next-'A']) continue;

        dfs(nx, ny, cnt+1, curVisit);
    }
    ans = max(cnt, ans);
}

int main() {
    scanf("%d %d", &r, &c);
    for(int i=0; i<r; i++) {
        scanf("%s", board[i]);
    }

    bool visit[27];
    memset(visit, false, sizeof(visit));
    dfs(0,0,1, visit);

    printf("%d\n", ans);
}
