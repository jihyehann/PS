#include <string>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

struct cmp {
    bool operator()(vector<int> a, vector<int> b) {
        return a[1] > b[1];
    }
};

int solution(vector<vector<int>> jobs) {
    int answer = 0;
    int size = jobs.size();
    sort(jobs.begin(), jobs.end()); // 오름차순 정렬
    
    priority_queue<vector<int>, vector<vector<int>>, cmp> pq;
        
    int endTime = 0;
    int cnt = 0;
    while(cnt < size || !pq.empty()) {
        while(cnt < size && jobs[cnt][0] <= endTime) {
            pq.push(jobs[cnt]);
            cnt++;
        }
        if(!pq.empty()) {
            answer += endTime - pq.top()[0] + pq.top()[1];
            endTime += pq.top()[1];
            pq.pop();
        }
        else { // 대기 중인 작업이 없는 경우 -> 가장 먼저 들어온 작업을 수행
            endTime = jobs[cnt][0] + jobs[cnt][1];
            answer += jobs[cnt][1];
            cnt++;
        }
    }
    
    return answer/size;
}
