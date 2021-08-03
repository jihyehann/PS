#include <string>
#include <vector>
using namespace std;
int ans = 0;
int t;
vector<int> n;
void dfs(int index, int value) {
    if (index == n.size() - 1) {
        if (value == t) ans++;
        return;
    }
    index++;
    dfs(index, value + n[index]);
    dfs(index, value - n[index]);
}
int solution(vector<int> numbers, int target) {
    n = numbers;
    t = target;
    dfs(0, n[0]);
    dfs(0, -n[0]);
    return ans;
}
