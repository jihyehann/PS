#include <iostream>
#include <vector>
#include <queue>
#include <limits.h> // INT_MAX
using namespace std;

vector<pair<int,int>> graph[51]; // 노드, 거리 (노드는 최대 50개)
int dist[51]; // 최단 거리

void dijkstra(int start) {
    priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>> pq;
    pq.emplace(0, start);
    while(!pq.empty()) {
        int cost = pq.top().first;
        int now = pq.top().second;
        pq.pop();

        if(cost > dist[now]) continue;

        for(pair<int,int> next : graph[now]) {
            int nextCost = cost + next.second;
            if(nextCost < dist[next.first]) {
                pq.emplace(nextCost, next.first);
                dist[next.first] = nextCost;
            }
        }
    }
}

int solution(int N, vector<vector<int>> road, int K) {
    for(vector<int> r : road) {
        graph[r[0]].emplace_back(r[1], r[2]);
        graph[r[1]].emplace_back(r[0], r[2]);
    }

    dist[1] = 0;
    for(int i=2; i<=N; i++) dist[i] = INT_MAX;
    dijkstra(1);

    int answer = 0;
    for(int i=1; i<=N; i++)
        if (dist[i] <= K) answer++;
    return answer;
}
