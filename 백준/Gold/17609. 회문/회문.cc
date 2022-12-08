#include <iostream>
using namespace std;

// 유사회문 검사
int checkPseudoPalindrome(string &str, int left, int right) {
    int flag = 2;
    for (int i=left, j=right; i<j; i++, j--) {
        if (str[i] != str[j]) {
            flag = 2;
            break;
        }
        flag = 1;
    }
    return flag;
}

int judge(string str) {
    int left = -1, right = -1;

    // 회문 검사
    for (int i=0; i<str.size()/2; i++) {
        if (str[i] != str[str.size()-1-i]) {
            left = i;
            right = str.size()-1-i;
            break;
        }
    }
    if (left == -1) { // 회문인 경우
        return 0;
    }

    if (checkPseudoPalindrome(str, left+1, right) == 1) return 1;
    return checkPseudoPalindrome(str, left, right-1);
}

int main() {
    ios::sync_with_stdio(false);

    int t;
    cin >> t;
    while (t--) {
        string str;
        cin >> str;
        cout << judge(str) << endl;
    }
    return 0;
}