#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n;
int memo[1500001] = {0};
vector<pair<int,int>> tp(1500000);

int main() {
    ios::sync_with_stdio(false);

    cin >> n;
    for (int i=0; i<n; i++) {
        cin >> tp[i].first >> tp[i].second;
    }

    if (tp[0].first <= n) {
        memo[tp[0].first] = tp[0].second;
    }
    for (int i=1; i<n; i++) {
        memo[i] = max(memo[i-1], memo[i]);
        if (tp[i].first + i <= n) {
            memo[tp[i].first + i] = max(memo[tp[i].first + i], memo[i] + tp[i].second);
        }
    }
    cout << max(memo[n-1], memo[n]) << endl;
}