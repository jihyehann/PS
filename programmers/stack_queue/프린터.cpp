#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(vector<int> priorities, int location) {
    int answer = 0;

    priority_queue<int> pq; // 중요도
    queue<pair<int,int>> q; // 중요도, 번호
    for(int i=0; i<priorities.size(); i++) {
        pq.push(priorities[i]);
        q.push({priorities[i], i});
    }
    for(int i=1; !pq.empty();) {
        pair<int,int> cur = q.front();
        q.pop();
        if(pq.top() > cur.first) q.push(cur);
        else {
            if(cur.second == location) {
                answer = i; break;
            }
            pq.pop(); i++;
        }

    }
    return answer;
}
