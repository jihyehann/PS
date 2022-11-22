#include <string>
#include <vector>
#include <unordered_map>
using namespace std;

unordered_map<string, pair<string,int>> um; // 이름, 추천인, 판매량

void dfs(string seller, int money, vector<string> &enroll) {
    if (seller.compare("-") == 0) return;
    if (money == 0) return;

    um[seller].second += (money - money / 10);
    dfs(um[seller].first, money/10, enroll);
}

vector<int> solution(vector<string> enroll, vector<string> referral, vector<string> seller, vector<int> amount) {
    vector<int> answer;
    for (int i=0; i<enroll.size(); i++) {
        um[enroll[i]] = {referral[i],0};
    }
    for (int i=0; i<seller.size(); i++) {
        dfs(seller[i], amount[i] * 100, enroll);
    }
    for (string name : enroll) {
        answer.push_back(um[name].second);
    }
    return answer;
}