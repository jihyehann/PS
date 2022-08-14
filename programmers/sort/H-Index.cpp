#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int solution(vector<int> citations) {
    int answer = 0; // 초기값. 논문 인용 수가 0인 경우
    sort(citations.begin(), citations.end(), greater<int>()); //내림차순 정렬
    for(int i=0; i<citations.size(); i++) {
        if((i+1) <= citations[i]) answer = i+1;
        else break;
    }
    return answer;
}
