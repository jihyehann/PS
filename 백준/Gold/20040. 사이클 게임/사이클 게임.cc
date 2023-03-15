#include <cstdio>
using namespace std;
int par[500001];
int find(int x) {
	if (x == par[x])	return x;
	return par[x] = find(par[x]);
}
bool merge(int x, int y) {
	x = find(x);
	y = find(y);
	if (x == y) return true;
	par[y] = x;
	return false;
}
int main() {
	int n, m, ans = 0;
	scanf("%d %d", &n, &m);
	for (int i = 0; i < n; i++)
		par[i] = i;
	for (int i = 0; i < m; i++) {
		int a, b;
		scanf("%d %d", &a, &b);
		if (ans == 0 && merge(a, b))
			ans = i + 1;
	}
	printf("%d\n", ans);
}