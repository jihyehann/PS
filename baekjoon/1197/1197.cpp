#include <cstdio>
#include <algorithm>
#include <vector>
using namespace std;
struct edge {
    int start, end, cost;
};
int parent[10010];
vector<edge> edges;

int Find(int x) {
    if(parent[x] == x) return x;
    return parent[x] = Find(parent[x]);
}
void Union(int x, int y) {
    x = Find(x);
    y = Find(y);
    parent[x] = y;
}

bool cmp(edge e1, edge e2) {
    return e1.cost < e2.cost;
}

int main() {
    int v, e;
    scanf("%d %d", &v, &e);

    // 부모 노드 초기화
    for(int i=1; i<=v; i++) parent[i] = i;

    for(int i=0; i<e; i++) {
        edge e;
        scanf("%d %d %d", &e.start, &e.end, &e.cost);
        edges.push_back(e);
    }

    // kruskal의 알고리즘
    sort(edges.begin(), edges.end(), cmp);
    int ans = 0;
    for(int i=0; i<e; i++) {
        int x = Find(edges[i].start);
        int y = Find(edges[i].end);
        if(x != y) {
            Union(x, y);
            ans += edges[i].cost;
        }
    }

    printf("%d\n", ans);
    return 0;
}
