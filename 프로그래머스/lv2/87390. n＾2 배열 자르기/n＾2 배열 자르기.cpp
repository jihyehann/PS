#include <vector>
using namespace std;

vector<int> solution(int n, long long left, long long right) {
    vector<int> answer(right-left+1);
    int index = 0;
    for (long long i=left; i<=right; i++) {
        int x = i / n;
        int y = i % n;
        if (x <= y) answer[index++] = y+1;
        else answer[index++] = x+1;
    }
    return answer;
}