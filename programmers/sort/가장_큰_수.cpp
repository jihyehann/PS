#include <string>
#include <vector>
#include <algorithm>
using namespace std;

bool cmp(string a, string b) {
    return a+b > b+a;
}
string solution(vector<int> numbers) {
    string answer = "";
    vector<string> str;
    for(int num : numbers)  str.emplace_back(to_string(num));
    sort(str.begin(), str.end(), cmp);
    if(str[0][0] == '0') return "0"; // 예외처리
    for(string s : str) answer += s;
    return answer;
}
