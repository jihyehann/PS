#include <cstdio>
#include <algorithm>
using namespace std;
int num[100001];
int main() {
    int n;
    scanf("%d", &n);
    for(int i=0; i<n; i++) scanf("%d", &num[i]);

    int l = 0, r = n-1, minSum = 2000000000;
    pair<int,int> ans;
    sort(num, num+n);
    while (1){
        if(l >= r) break;
        int sum = num[l] + num[r];
        if(abs(sum) < minSum) {
            minSum = abs(sum);
            ans = {num[l], num[r]};
            if(sum == 0) break;
        }
        if(sum < 0) l++;
        else r--;
    }
    printf("%d %d\n", ans.first, ans.second);
}