#include <vector>
#include <algorithm>
#include <set>
using namespace std;

int solution(int N, int number) {
	//N을 i+1번 나열한 값으로 초기화
	vector<set<int>> dp(8);
	for (int i = 0; i < 8; i++) {
		int tmp = 0;
		for (int j = i + 1; j > 0; j--) {
			tmp = tmp * 10 + N;
		}
		dp[i].insert(tmp);
		if (tmp == number)
			return i + 1;
	}

	for (int i = 1; i < 8; i++) {
		for (int j = 0; j < i; j++) {
			for (int x : dp[j]) {
				for (int y : dp[i - 1 - j]) {
					dp[i].insert(x + y);
					dp[i].insert(x - y);
					dp[i].insert(x * y);
					dp[i].insert(y + x);
					if (y != 0)	dp[i].insert(x / y);
					if (x != 0)	dp[i].insert(y / x);
				}
			}
		}
		if (dp[i].find(number) != dp[i].end())
			return i + 1;
	}
	return -1;
}
