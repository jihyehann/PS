#include <iostream>
#include <vector>
using namespace std;

int n;
char map[7][7];
vector<pair<int,int>> teacher;

bool check() {
    int mv[4][2] = {{-1,0}, {1,0}, {0,-1}, {0,1}}; // 상하좌우

    for (pair<int,int> t : teacher) {
        for (int i=0; i<4; i++) {
            int x = t.first + mv[i][0];
            int y = t.second + mv[i][1];
            while (x>=0 && x<n && y>=0 && y<n) {
                if (map[x][y] == 'O') break;
                if (map[x][y] == 'S') return false;
                x += mv[i][0];
                y += mv[i][1];
            }
        }
    }
    return true;
}

bool addObstacle(int cnt) {
    if (cnt == 3) {
        return check();
    }

    for (int i=0; i<n; i++) {
        for (int j=0; j<n; j++) {
            if (map[i][j] != 'X') continue;
            map[i][j] = 'O';
            if (addObstacle(cnt+1)) {
                return true;
            }
            map[i][j] = 'X';
        }
    }

    return false;
}

void input() {
    ios::sync_with_stdio(false);
    cin >> n;
    for (int i=0; i<n; i++) {
        for (int j=0; j<n; j++) {
            cin >> map[i][j];
            if (map[i][j] == 'T') {
                teacher.emplace_back(i,j);
            }
        }
    }
}

int main() {
    input();
    if (addObstacle(0)) {
        cout << "YES" << endl;
    } else {
        cout << "NO" << endl;
    }

}
