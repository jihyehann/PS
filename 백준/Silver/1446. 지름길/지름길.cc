#include <iostream>
#include <queue>
#include <algorithm>
using namespace std;

vector<pair<int,int>> path[10001];
int dp[10001];

int main() {
    int n, destination; // 지름길 개수, 고속도로 길이
    cin >> n >> destination;
    for(int i=0; i<n; i++) {
        int start, end, length;
        cin >> start >> end >> length;
        path[end].emplace_back(start, length);
    }

    for(int i=1; i<=destination; i++) dp[i] = i;

    for(int i=1; i<=destination; i++) {
        if(path[i].empty()) dp[i] = dp[i-1] + 1;
        else {
            for(pair<int,int> p : path[i]) {
                dp[i] = min(dp[i], min(dp[i-1]+1, dp[p.first] + p.second));
            }
        }
    }

   cout << dp[destination] << endl;
}
