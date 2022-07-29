#include <string>
#include <vector>
#include <queue>
#include <cmath>
using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    int size = progresses.size();
    queue<int> q;
    vector<int> answer;
    for(int i=0; i<size; i++) {
        int days = ceil((100.0 - progresses[i])/speeds[i]);
        q.push(days);
    }
    while(!q.empty()) {
        int cur = q.front();
        int cnt = 1;
        q.pop();
        while(!q.empty() && cur >= q.front()) {
            cnt++;
            q.pop();
        }
        answer.push_back(cnt); 
    }
    return answer;
}
