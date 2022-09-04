#include <string>
#include <vector>
#include <algorithm>
#include <sstream>
#include <unordered_map>
using namespace std;

unordered_map<string, vector<int>> um;

void addAllCase(string* input, int score) {
    for(int i=0; i<16; i++) { // 모든 경우의 수 16가지
        string str = "";
        for (int j = 0; j < 4; j++)
            str += (i & (1 << j)) ? "-" : input[j];
        um[str].emplace_back(score);
    }
}

vector<int> solution(vector<string> info, vector<string> query) {
    vector<int> answer;
    
    for(string i : info) {
        istringstream iss(i);
        string str[4];
        int score;
        iss >> str[0] >> str[1] >> str[2] >> str[3] >> score; // 공백으로 구분
        addAllCase(str, score);
    }

    // score를 기준으로 오름차순 정렬
    for(auto iter = um.begin(); iter != um.end(); iter++) {
        sort(iter->second.begin(), iter->second.end());
    }

    for(string q : query) {
        string str[4], tmp;
        int score;
        istringstream iss(q);
        iss >> str[0] >> tmp >> str[1] >> tmp >> str[2] >> tmp >> str[3] >> score;
        vector<int> scores = um[str[0]+str[1]+str[2]+str[3]]; // 조건에 맞는 점수 리스트
        if(scores.empty()) answer.emplace_back(0);
        else {
            int index = lower_bound(scores.begin(), scores.end(), score) - scores.begin();
            answer.emplace_back(scores.size() - index);
        }
    }
    return answer;
}
