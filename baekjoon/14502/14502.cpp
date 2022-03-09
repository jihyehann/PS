#include <iostream>
#include <queue>
using namespace std;
int n, m;
int mv[4][2] = {{1,0}, {-1, 0}, {0, 1}, {0, -1}};
int ans = 0;

void count_safe_area(int mat[8][8]) {
    int cnt = 0;
    for(int i=0; i<n; i++)
        for(int j=0; j<m; j++)
            if (mat[i][j] == 0)
                cnt++;
    ans = max(ans, cnt);
}

void search(int mat[8][8]) {
    int temp[8][8], visit[8][8];
    queue<pair<int, int>> q;

    for(int i=0; i<n; i++)
        for(int j=0; j<m; j++) {
            temp[i][j] = mat[i][j];
            visit[i][j] = 0;
        }

    for(int i=0; i<n; i++)
        for(int j=0; j<m; j++)
            if(temp[i][j] == 2)
                q.push(make_pair(i, j));

    while(!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        for(int i=0; i<4; i++) {
            int nx = x + mv[i][0];
            int ny = y + mv[i][1];

            if(nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if(visit[nx][ny] == 1 || temp[nx][ny] == 1)
                    continue;
                if(temp[nx][ny] == 0) {
                    temp[nx][ny] = 2;
                    q.push(make_pair(nx, ny));
                }
            }
        }
    }

    count_safe_area(temp);
}


void make_wall(int cnt, int mat[8][8]) {
    int temp[8][8];

    if(cnt == 3) {
        search(mat);
        return;
    }

    for(int i=0; i<n; i++)
        for(int j=0; j<m; j++)
            temp[i][j] = mat[i][j];


    for(int i=0; i<n; i++) {
        for(int j=0; j<m; j++) {
            if(temp[i][j] == 0) {
                temp[i][j] = 1;
                make_wall(cnt+1, temp);
                temp[i][j] = 0;
            }
        }
    }
}

int main() {
    int mat[8][8];
    cin >> n >> m;
    for(int i=0; i<n; i++)
        for(int j=0; j<m; j++){
            cin >> mat[i][j];
        }

    make_wall(0, mat);
    cout << ans << endl;

    return 0;
}
