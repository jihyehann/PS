#include <string>
#include <vector>
#include <queue>

using namespace std;

vector<int> solution(vector<string> operations) {
    int size = 0;
    priority_queue<int, vector<int>, greater<int>> minHeap;
    priority_queue<int, vector<int>, less<int>> maxHeap;
    for(auto iter = operations.begin(); iter != operations.end(); iter++) {
        string op = *iter;
        if(op[0] == 'I') { // 삽입
            int num = stoi(&op[2]);
            minHeap.push(num);
            maxHeap.push(num);
            size++;
        } else if(op[0] == 'D' && op[2] == '-' && size > 0) { // 최솟값 삭제
            minHeap.pop();
            size--;
        } else if(size > 0) { // 최댓값 삭제
            maxHeap.pop();
            size--;
        }
        if(size == 0) {
            if(!minHeap.empty()) minHeap.pop();
            if(!maxHeap.empty()) maxHeap.pop();
        }
    }
    if(size < 1) {
        return {0,0};
    }
    return {maxHeap.top(), minHeap.top()};
}
