#include <vector>
using namespace std;

vector<int> solution(vector<int> prices) {
    int size = prices.size();
    vector<int> answer(size);
    vector<pair<int,int>> stack; // 인덱스, 값
    stack.emplace_back(0, prices[0]);
  
    for (int i = 1; i < size; i++) {
        while(prices[i] < stack.back().second) {
            answer[stack.back().first] = i - stack.back().first;
            stack.pop_back();
        }
        stack.emplace_back(i, prices[i]);
    }

    while(!stack.empty()) {
        int index = stack.back().first;
        answer[index] = size - index - 1;
        stack.pop_back();
    }

    return answer;
}
