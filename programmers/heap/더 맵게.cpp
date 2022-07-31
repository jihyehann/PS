#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(vector<int> scoville, int K) {
    int answer = 0;
    priority_queue<int, vector<int>, greater<int>> pq;
    for(auto iter = scoville.begin(); iter != scoville.end(); iter++) {
        pq.push(*iter);
    }
    while(1) {
        if(pq.size() < 2) return -1;
        if(pq.top() >= K) break;
        answer++;
        int first = pq.top();
        pq.pop();
        int second = pq.top();
        pq.pop();
        pq.push(first + second*2);
    }
    return answer;
}
