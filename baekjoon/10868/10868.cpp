#include <cstdio>
#include <algorithm>
using namespace std;
const int MAX_SIZE = 1 << 20;
int segTree[MAX_SIZE*2];

void makeSegTree() {
    for(int i=MAX_SIZE-1; i>0; i--)
        segTree[i] = min(segTree[i*2], segTree[i*2+1]);
}

int findMin(int n, int nl, int nr, int l, int r) {
    if(nr < l || nl > r) return 1<<30;
    if(nl >= l && nr <= r) return segTree[n];
    int m = (nl+nr+1)/2;
    return min(findMin(n*2, nl, m-1, l, r), findMin(n*2+1, m, nr, l, r));
}

int main() {
    int n, m;
    scanf("%d %d", &n, &m);

    // 노드 입력 -> 세그먼트 트리의 리프 노드 구성
    for(int i=1; i<=n; i++)
        scanf("%d", &segTree[MAX_SIZE+i]);

    // 세그먼트 트리 생성
    makeSegTree();

    // 구간 입력
    for(int i=0; i<m; i++) {
        int a, b;
        scanf("%d %d", &a, &b);
        printf("%d\n", findMin(1, 0, MAX_SIZE-1, a, b));
    }
}
