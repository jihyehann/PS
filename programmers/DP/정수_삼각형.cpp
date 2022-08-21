#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int solution(vector<vector<int>> triangle) {
	int answer = 0;
	int h = triangle.size();
	for (int i = 1; i < h; i++) {
		int s = triangle[i].size();
		triangle[i][0] += triangle[i - 1][0];
		triangle[i][s - 1] += triangle[i - 1][s - 2];
		for (int j = 1; j < s - 1; j++) {
			triangle[i][j] += max(triangle[i - 1][j - 1], triangle[i - 1][j]);
		}
	}
	int s = triangle[h - 1].size();
	for (int i = 0; i < s; i++)
		answer = max(answer, triangle[h - 1][i]);
	return answer;
}
