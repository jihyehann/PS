#include <string>
#include <vector>
#include <algorithm>
#include <cstdlib>
using namespace std;
vector<vector<int>> edges(101);
int connect[101];
int dfs(int now, int prev) {
    connect[now] = 1;
    for(auto node : edges[now]) {
        if(node == prev) continue;
        connect[now] += dfs(node, now);
    }
    return connect[now];
}

int solution(int n, vector<vector<int>> wires) {
    int answer = 100;
    for(auto wire : wires) {
        edges[wire[0]].emplace_back(wire[1]);
        edges[wire[1]].emplace_back(wire[0]);
    }
    dfs(1, 1);
    for(int i=1; i<=n; i++) {
        answer = min(answer, abs(2*connect[i] - n));
    }
    return answer;
}
