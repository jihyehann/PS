#include <string>
#include <vector>
#include <algorithm>
using namespace std;

bool cmp(vector<int> a, vector<int> b) {
    if(a[0] < b[0]) return true;
    if(a[0] == b[0] && a[1] < b[1]) return true;
    return false;
}

int solution(int k, vector<vector<int>> dungeons) {
    int answer = -1;
    sort(dungeons.begin(), dungeons.end(), cmp);
    do {
        int cur = k, cnt = 0;
        for(auto dungeon : dungeons) {
            if(cur < dungeon[0]) break;
            cur -= dungeon[1];
            cnt++;
        }
        answer = max(answer, cnt);
    } while (next_permutation(dungeons.begin(), dungeons.end()));
    
    return answer;
}
