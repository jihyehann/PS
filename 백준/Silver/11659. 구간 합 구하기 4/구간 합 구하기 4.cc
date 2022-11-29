#include <cstdio>
using namespace std;

int arr[100001] = {0};
int main() {
    int N, M;
    scanf("%d %d", &N, &M);

    for (int i=1; i<=N; i++) {
        scanf("%d", &arr[i]);
        arr[i] += arr[i-1];
    }

    for (int i=0; i<M; i++) {
        int left, right;
        scanf("%d %d", &left, &right);
        printf("%d\n", arr[right] - arr[left-1]);
    }
}