#include <vector>
#include <queue>
using namespace std;

int solution(vector<int> queue1, vector<int> queue2) {

    long total = 0, l = 0, r = 0;
    queue<int> q1, q2;

    for (int i = 0; i < queue1.size(); i++) {
        total += queue1[i];
        l += queue1[i];
        q1.push(queue1[i]);
    }
    for (int i = 0; i < queue2.size(); i++) {
        total += queue2[i];
        r += queue2[i];
        q2.push(queue2[i]);
    }
    if(total%2) return -1;

    for(int i=0; i<total; i++) {
        if(l == r) return i;
        if(l > r) {
            r += q1.front();
            l -= q1.front();
            q2.push(q1.front());
            q1.pop();
        }
        else {
            l += q2.front();
            r -= q2.front();
            q1.push(q2.front());
            q2.pop();
        }
    }

    return -1;
}
