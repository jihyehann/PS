#include <iostream>
#include <queue>
#include <algorithm>
#define MAX_VALUE 3000
using namespace std;

int n, m, map[51][51];
vector<pair<int,int>> virus;

void input() {
    ios::sync_with_stdio(false);
    cin >> n >> m;
    for (int i=0; i<n; i++) {
        for (int j=0; j<n; j++) {
            cin >> map[i][j];
            if (map[i][j] == 2) {
                virus.emplace_back(i, j);
            }
        }
    }

}

bool check(int map[51][51]) {
    for (int i=0; i<n; i++) {
        for (int j=0; j<n; j++) {
            if (map[i][j] == 0) return false;
        }
    }
    return true;
}

int getTime(vector<int> v) {
    bool visit[51][51] = {false};
    int mv[4][2] = {{1,0},{-1,0},{0,1},{0,-1}};

    int map2[51][51];
    for (int i=0; i<n; i++) {
        for (int j=0; j<n; j++) {
            map2[i][j] = map[i][j];
        }
    }
    queue<tuple<int,int,int>> q;
    for (int i : v) {
        q.emplace(virus[i].first, virus[i].second, 0);
        visit[virus[i].first][virus[i].second] = true;
    }

    while (!q.empty()) {
        int x = get<0>(q.front());
        int y = get<1>(q.front());
        int cnt = get<2>(q.front());
        map2[x][y] = 2;

        if (check(map2)) {
            return cnt;
        }
        q.pop();

        for (int i=0; i<4; i++) {
            int nx = x + mv[i][0];
            int ny = y + mv[i][1];
            if (nx<0 || nx>=n || ny<0 || ny>=n) continue;
            if (map2[nx][ny] == 1 || visit[nx][ny]) continue;
            visit[nx][ny] = true;
            q.emplace(nx, ny, cnt+1);
        }
    }

    return -1; // 모든 빈 칸에 바이러스를 퍼뜨릴 수 없는 경우
}

int selectVirusAndGetTime(int index, vector<int> v) {
    v.emplace_back(index);
    if (v.size() == m) {
        return getTime(v);
    }

    int ans = MAX_VALUE;
    for (int i=index+1; i<virus.size(); i++) {
        int cnt = selectVirusAndGetTime(i, v);
        if (cnt != -1) {
            ans = min(ans, cnt);
        }
    }
    if (ans == MAX_VALUE) return -1;
    return ans;
}

int main() {
    input();

    if (virus.size() <= m) {
        vector<int> v;
        for (int i=0; i<virus.size(); i++) v.emplace_back(i);
        cout << getTime(v) << endl;
    }
    else {
        int ans = MAX_VALUE;
        for (int i=0; i<=virus.size()-m; i++) {
            int cnt = selectVirusAndGetTime(i, {});
            if (cnt != -1) {
                ans = min(ans, cnt);
            }
        }
        if (ans == MAX_VALUE) {
            cout << -1 << endl;
        } else {
            cout << ans << endl;
        }
    }

}