#include <vector>
#include <algorithm>

using namespace std;

bool isPossible(int a, int b, vector<int> g, vector<int> s, vector<int> w, vector<int> t, long long mid) {
    long long gold = 0, silver = 0, sum = 0;

    for (int i=0; i<g.size(); i++) {
        long long maxWeight = (1 + (mid-t[i])/(2*t[i])) * w[i]; // mid 시간 동안 최대 이동 횟수 * 운반 가능 무게
        gold += min((long long)g[i], maxWeight);
        silver += min((long long)s[i], maxWeight);
        sum += min((long long)g[i] + s[i], maxWeight);
    }

    if (gold >= a && silver >= b && sum >= a + b) return true;
    return false;
}


long long solution(int a, int b, vector<int> g, vector<int> s, vector<int> w, vector<int> t) {
    long long left = 0;
    long long right = 10e14*2;
    long long answer = right;
    while (left <= right) {
        long long mid = (left + right) / 2;
        if (isPossible(a, b, g, s, w, t, mid)) {
            answer = min(answer, mid);
            right = mid - 1;
        } else {
            left = mid + 1;
        }

    }
    return answer;
}