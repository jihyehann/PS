#include <vector>
#include <queue>
using namespace std;

int solution(int bridge_length, int weight, vector<int> truck_weights) {

    queue<pair<int,int>> q;
    q.push({truck_weights[0], 0});
    int cur = 0, sum = truck_weights[0];
    for(int i=1; i<truck_weights.size(); i++) {
        while(true) {
            cur++;
            if(cur - q.front().second >= bridge_length) {
                sum -= q.front().first;
                q.pop();
            }
            if(q.size() == bridge_length || sum + truck_weights[i] > weight) continue;
            q.push({truck_weights[i], cur});
            sum += truck_weights[i];
            break;
        }
    }

    while(!q.empty()) {
        pair<int,int> p = q.front();
        if(cur - p.second <= bridge_length) {
            cur = bridge_length + p.second + 1;
        }
        q.pop();
    }
    return cur;
}
