#include <iostream>
#include <cstring>
#include <algorithm>
using namespace std;
int n, m, board[501][501], mv[4][2] = { {-1,0},{1,0},{0,-1},{0,1} };
int ans = 0;

int dfs(int x, int y, int cnt, int sum, int par) {
	sum += board[x][y];
	if (cnt == 4) {
		ans = max(ans, sum);
		return sum;
	}
		
	for (int i = 0; i < 4; i++) {
		if (i == par) continue;

		int nx = x + mv[i][0];
		int ny = y + mv[i][1];
		if (nx < 0 || nx >= n || ny < 0 || ny >= m)
			continue;

		int now;
		if (i == 0) now = 1;
		else if (i == 1) now = 0;
		else if (i == 2) now = 3;
		else if (i == 3) now = 2;

		int sum2 = dfs(nx, ny, cnt + 1, sum, now);
		if (cnt == 2) {
			for (int j = 0; j < 4; j++) {
				if (i == j || j == par) continue;
				nx = x + mv[j][0];
				ny = y + mv[j][1];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m)
					continue;
				dfs(nx, ny, 4, sum2, 0);
			}
		}
			
	}
	return sum;
}
int main() {
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++)
			cin >> board[i][j];
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			dfs(i, j, 1, 0, 4);
		}
	}
	cout << ans << endl;
}
