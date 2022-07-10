#include <iostream>
#include <queue>
#include <set>
#include <algorithm>
using namespace std;
string number;
int k, len;

void swap(string& input, int i, int j) {
    int temp = input[i];
    input[i] = input[j];
    input[j] = temp;
}

int main() {
    cin >> number >> k;
    len = number.length();

    if(len == 1 || (len == 2 && number[1] == '0')) {
        cout << -1 << endl;
        return 0;
    }

    queue<string> q;
    q.push(number);

    // k번 교환
    for(int i=0; i<k; i++) {
        set<string> visit;
        int size = q.size();
        for(int j=0; j<size; j++) {
            string cur = q.front();
            q.pop();

            // 이미 방문한 경우
            if(visit.find(cur) != visit.end()) continue;
        
            visit.insert(cur);

            for(int l=0; l < cur.size()-1; l++) {
                for(int m=l+1; m < cur.size(); m++) {
                    // 맨 앞은 0이 될 수 없음
                    if(l==0 && cur[m] == '0') continue;

                    swap(cur, l, m);
                    q.push(cur);
                    swap(cur, l, m);
                }
            }
        }
    }

    string ans = "0";
    while(!q.empty()) {
        ans = max(ans, q.front());
        q.pop();
    }

    cout << ans << endl;

}
