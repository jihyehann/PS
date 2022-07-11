#include <iostream>
#include <cstring>
using namespace std;
int n,m,mat[301][301];
int mv[4][2] = {{1,0},{-1,0},{0,1},{0,-1}};
bool finish = false;
bool visit[301][301];

int countEmpty(int x, int y) {
    int cnt = 0;
    for(int i=0; i<4; i++) {
        int nx = x + mv[i][0];
        int ny = y + mv[i][1];
        if(nx < 0 || nx >= n || ny <0 || ny >= m) cnt++;
        else if(mat[nx][ny] == 0) cnt++;
    }
    return cnt;
}

void dfs(int x, int y) {
    visit[x][y] = true;
    for(int i=0; i<4; i++) {
        int nx = x + mv[i][0];
        int ny = y + mv[i][1];
        if(nx < 0 || nx >= n || ny <0 || ny >= m) continue;
        if(visit[nx][ny] || mat[nx][ny] == 0) continue;
        dfs(nx, ny);
    }
}

bool seperate() {
    int group = 0;
    memset(visit, false, sizeof(visit));
    for(int i=0; i<n; i++) {
        for(int j=0; j<m; j++) {
            if(visit[i][j] == false && mat[i][j] != 0) {
                group++;
                dfs(i, j);
            }
            if(group >= 2) return true;
        }
    }
    return false;
}


bool solve() {
    int temp[301][301], size = 0;
    for(int i=0; i<n; i++) {
        for(int j=0; j<m; j++) {
            if(mat[i][j] != 0) {
                size++;
                int cnt = countEmpty(i,j);
                temp[i][j] = (mat[i][j] >= cnt) ? mat[i][j]-cnt : 0;
            } else {
                temp[i][j] = 0;
            }
        }
    }

    // 빙산이 다 녹은 경우
    if(size == 0) return false;

    for(int i=0; i<n; i++) {
        for(int j=0; j<m; j++) {
            mat[i][j] = temp[i][j];
        }
    }
    return true;
}

int main() {
    cin >> n >> m;
    for(int i=0; i<n; i++)
        for(int j=0; j<m; j++)
            cin >> mat[i][j];
    mat[0][0] = mat[n-1][m-1] = 0;

    int ans = 1;
    for(; ; ans++) {

        // 빙산이 다 녹은 경우
        if(solve() == false) {
            cout << 0 << endl;
            return 0;
        }
        // 2개 이상의 덩어리로 분리된 경우
        if(seperate()) {
            cout << ans << endl;
            return 0;
        }
    }
}
