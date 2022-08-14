#include <string>
#include <vector>
#include <cstring>
using namespace std;
bool visit[201];
int N;
vector<vector<int>> v;
void dfs(int x) {
	visit[x] = true;
	for (int i = 0; i < N; i++) {
		if (visit[i] == false && v[x][i] == 1) {
			dfs(i);
		}
	}
}
int solution(int n, vector<vector<int>> computers) {
	int answer = 0;
	memset(visit, false, sizeof(visit));
	N = n;
	v = computers;
	for (int i = 0; i < n; i++) {
		if (visit[i] == false) {
			dfs(i);
			answer++;
		}
	}
	return answer;
}
