#include <string>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

int type[50001] = {0}; // 0 쉼터, 1 출입구, 2 산봉우리
vector<pair<int,int>> node[50001]; // 노드 번호, cost
vector<int> answer = {50001, 123456789}; // 산봉우리, intensity
int intensity[50001]; // 노드별 intensity

vector<int> solution(int n, vector<vector<int>> paths, vector<int> gates, vector<int> summits) {

    for(vector<int> path : paths) { // 양방향 edge
        node[path[0]].emplace_back(path[1], path[2]);
        node[path[1]].emplace_back(path[0], path[2]);
    }

    priority_queue<pair<int,int>> pq; // intensity, 노드

    for(int g : gates) {
        type[g] = 1;
        pq.emplace(0,g); // intensity, 노드 - 모든 출입구 삽입
    }
    for(int s : summits) type[s] = 2;
    for(int i=1; i<=n; i++) intensity[i] = 123456789;

    while(!pq.empty()) {
        int maxIntensity = pq.top().first;
        int cur = pq.top().second;
        pq.pop();

        if(maxIntensity > answer[1]) continue;

        if(type[cur] == 2) { // 산봉우리인 경우
            if (maxIntensity < answer[1] || (maxIntensity == answer[1] && cur < answer[0]))
                answer = {cur, maxIntensity}; // 갱신
            continue;
        }

        for(pair<int,int> next : node[cur]) {
            int nextIntensity = max(maxIntensity, next.second);
            if(intensity[next.first] > nextIntensity) { // intensity 갱신
                intensity[next.first] = nextIntensity;
                pq.emplace(nextIntensity, next.first);
            }
        }
    }

    return answer;
}
