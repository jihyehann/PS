#include <cstdio>
#include <queue>
#include <cstring>
using namespace std;
int n, m;
char board[12][12];
int rX, rY, bX, bY;
int mv[4][2] = { {-1,0}, {1,0},{0,-1},{0,1} };
bool visit[12][12][12][12];

int bfs() {
	queue<pair<pair<int, int>, int>> red; // x, y, cnt
	queue<pair<int, int>> blue;

	red.push(make_pair(make_pair(rX, rY), 0));
	blue.push(make_pair(bX, bY));
	visit[rX][rY][bX][bY] = true;

	while (!red.empty()) {
		pair<int, int> rxy = red.front().first;
		pair<int, int> bxy = blue.front();
		int cnt = red.front().second;
		if (cnt > 10) return -1;
		red.pop();
		blue.pop();

		if (board[rxy.first][rxy.second] == 'O') {
			return cnt;
		}

		for (int i = 0; i < 4; i++) {
			int rx = rxy.first + mv[i][0];
			int ry = rxy.second + mv[i][1];
			int bx = bxy.first + mv[i][0];
			int by = bxy.second + mv[i][1];
			
			while (board[rx][ry] == '.') {
				rx = rx + mv[i][0];
				ry = ry + mv[i][1];
			}
			if (board[rx][ry] == '#') {
				rx = rx - mv[i][0];
				ry = ry - mv[i][1];
			}
			
			while (board[bx][by] == '.') {
				bx = bx + mv[i][0];
				by = by + mv[i][1];
			}
			if (board[bx][by] == '#') {
				bx = bx - mv[i][0];
				by = by - mv[i][1];
			}

			if (board[bx][by] == 'O')
				continue;
			if (rx == bx && ry == by) {
				switch (i) {
				case 0:
					if (rxy.first > bxy.first) rx++;
					else bx++;
					break;
				case 1:
					if (rxy.first > bxy.first) bx--;
					else rx--;
					break;
				case 2:
					if (rxy.second < bxy.second) by++;
					else ry++;
					break;
				case 3:
					if (rxy.second > bxy.second) by--;
					else ry--;
					break;
				}
			}
			
			if (visit[rx][ry][bx][by] == false) {
				visit[rx][ry][bx][by] = true;
				red.push(make_pair(make_pair(rx, ry), cnt + 1));
				blue.push(make_pair(bx, by));
			}
		}
	}
	return -1;
}
int main() {
	scanf("%d %d", &n, &m);
	for (int i = 0; i < n; i++) {
		scanf("%s", board[i]);
		for (int j = 0; j < m; j++) {
			if (board[i][j] == 'R') {
				rX = i;
				rY = j;
				board[i][j] = '.';
			}
			else if (board[i][j] == 'B') {
				bX = i;
				bY = j;
				board[i][j] = '.';
			}
		}
	}
	memset(visit, false, sizeof(visit));
	printf("%d\n", bfs());
}
