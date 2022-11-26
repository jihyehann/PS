#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> lottos, vector<int> win_nums) {
    int correct = 0;
    int zero = 0;
    for (int lotto : lottos) {
        if (lotto == 0) {
            zero++;
            continue;
        }
        if (find(win_nums.begin(), win_nums.end(), lotto) != win_nums.end()) {
            correct++;
        }

    }
    int maxRank, minRank;
    switch (correct + zero) {
        case 6: maxRank=1; break;
        case 5: maxRank=2; break;
        case 4: maxRank=3; break;
        case 3: maxRank=4; break;
        case 2: maxRank=5; break;
        default: maxRank=6;
    }
    switch (correct) {
        case 6: minRank=1; break;
        case 5: minRank=2; break;
        case 4: minRank=3; break;
        case 3: minRank=4; break;
        case 2: minRank=5; break;
        default: minRank=6;
    }
    return {maxRank, minRank};
}