#include <iostream>
using namespace std;

int N, M, R;
int map[101][101];
bool check[101][101] = {false};
int cnt = 0;

void printMap() {
    for (int i=1; i<=N; i++) {
        for (int j=1; j<=M; j++) {
            cout << (check[i][j] ? "F " : "S ");
        }
        cout << endl;
    }
}

void push(int &x, int &y, int &dir) {
    static int mv[4][2] ={{0,1},{0,-1},{1,0},{-1,0}};

    check[x][y] = true;
    cnt++;

    int nx = x;
    int ny = y;
    for (int i=1; i<map[x][y]; i++) {
        nx = nx + mv[dir][0];
        ny = ny + mv[dir][1];
        if (nx < 1 || nx > N || ny < 1 || ny > M) break;
        if (check[nx][ny]) continue;
        push(nx, ny, dir);
    }
}

void putUp(int &x, int &y) {
    check[x][y] = false;
}

int main() {
    cin >> N >> M >> R;
    for (int i=1; i<=N; i++) {
        for (int j=1; j<=M; j++) {
            cin >> map[i][j];
        }
    }

    while (R--) {
        int x, y;
        char d;
        cin >> x >> y >> d;
        int dir;
        switch (d) {
            case 'E': dir = 0;
                break;
            case 'W': dir = 1;
                break;
            case 'S': dir = 2;
                break;
            case 'N': dir = 3;
                break;
        }
        if (!check[x][y]) {
            push(x, y, dir);
        }

        cin >> x >> y;
        putUp(x, y);
    }

    cout << cnt << endl;
    printMap();

    return 0;
}