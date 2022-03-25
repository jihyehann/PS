#include <iostream>
#include <cstring>
using namespace std;
int hori[40][20]; //세로 높이, 세로선
int n, m, h; //세로선, 가로선, 높이
int ans = 100, maxCnt;
bool check() {
    for(int i=1; i<=n; i++) {
        int x = 1, y = i;
        while(1) {
            if(x > h) break;
            if (hori[x][y] == 1) y++;
            else if (y != 1 && hori[x][y-1] == 1)  y--;
            x++;
        }
        if(i != y) return false;
    }
    return true;
}

void addHori(int cnt, int height) {

    if(cnt == maxCnt) {
        if(check()) ans = cnt;
        return;
    }

    for(int i = height; i <= h; i++) {
        for (int j = 1; j < n; j++) {
            if (hori[i][j] == 1 || hori[i][j - 1] == 1 || hori[i][j + 1] == 1){
                continue;
            }
            else {
                hori[i][j] = 1;
                addHori(cnt + 1, i);
                hori[i][j] = 0;
            }
        }
    }
    return;
}

int main() {
    cin >> n >> m >> h;
    memset(hori, 0, sizeof(hori));
    for(int i=0; i<m; i++) {
        int a, b;
        cin >> a >> b; // 위치, 세로선
        hori[a][b] = 1;
    }

    for(int i=0; i<=3; i++) {
        maxCnt = i;
        addHori(0, 0);
        if(ans < 4) break;
    }
    if(ans > 3) cout << -1 << endl;
    else cout << ans << endl;
}
