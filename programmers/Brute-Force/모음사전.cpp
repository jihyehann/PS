#include <string>
#include <vector>

using namespace std;

int getNum(int i) {
    int ret = 0;
    for(; i<4; i++) ret = ret*5+5;
    return ret;
}

int solution(string word) {
    int answer = 0;
    int len = word.length();
    for(int i=0; i<len; i++) {
        char c = word[i];
        switch (c){
        case 'A':
            answer++;
            break;
        case 'E':
            answer += getNum(i)+2;
            break;
        case 'I':
            answer += getNum(i)*2+3;
            break;
        case 'O':
            answer += getNum(i)*3+4;
            break;
        default:
            answer += getNum(i)*4+5;
            break;
        }
    }
    return answer;
}
