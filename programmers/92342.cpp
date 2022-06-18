#include <string>
#include <vector>
using namespace std;

vector<int> answer;
vector<int> apeach;
int ansDiff = 0;

int getDiff(vector<int> score) {
    int rs = 0, as = 0; 
    for(int i=0; i<10; i++) {
        if(score[i] > apeach[i]) rs += (10-i);
        else if(apeach[i] != 0) as += (10-i);
    }
    return rs-as;
}

void dfs(int limit, int index, vector<int> score) {
    if(limit < 1 || index > 10) {
        int diff = getDiff(score);
        
        // 어피치가 우승한 경우
        if(diff < 1) return;
        
        if(diff > ansDiff) {
            answer = score; ansDiff = diff;
        } 
        else if(diff == ansDiff) {
            for(int i=10; i>=0; i--) {
                if(answer[i] > score[i]) break;
                if(score[i] > answer[i]) {
                    answer = score; ansDiff = diff;
                    break;
                }
            }
        }
        return;
    }
    
    for(int i=index; i<=10; i++) {
        if(i == 10 && limit > 0) {
            score[i] = limit;
            dfs(limit, i+1, score);
        }
        else if(limit - (apeach[i] + 1) >= 0) {
            score[i] = apeach[i] + 1;
            dfs(limit - (apeach[i] + 1), i+1, score);
            score[i] = 0;
        }
    }
}

vector<int> solution(int n, vector<int> info) {
    apeach = info;
    dfs(n, 0, {0,0,0,0,0,0,0,0,0,0,0});
    
    // 라이언이 우승할 수 없는 경우
    if(answer.empty()) answer.push_back(-1);
    
    return answer;
}
