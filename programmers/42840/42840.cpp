#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

vector<int> pattern1 = {1,2,3,4,5}; 
vector<int> pattern2 = {2,1,2,3,2,4,2,5};
vector<int> pattern3 = {3,3,1,1,2,2,4,4,5,5};

vector<int> eval(vector<int> answers){  
    int cnt1 = 0, cnt2 = 0, cnt3 = 0;
    int len = answers.size();
    vector<int> win;
    for(int i=0; i<len; i++){
        if(pattern1[i%5] == answers[i])  cnt1++;
        if(pattern2[i%8] == answers[i])  cnt2++;
        if(pattern3[i%10] == answers[i])  cnt3++;
    }    

    int maximum = max(cnt1, max(cnt2, cnt3));
    if(maximum == cnt1) win.push_back(1);
    if(maximum == cnt2) win.push_back(2);
    if(maximum == cnt3) win.push_back(3);
    return win;
}

vector<int> solution(vector<int> answers) {
    vector<int> answer;
    answer = eval(answers);
    return answer;
}
