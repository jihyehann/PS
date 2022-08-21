#include <string>
#include <vector>

using namespace std;

vector<int> solution(int brown, int yellow) {
    vector<int> answer;
    int yRow, yCol; // row <= col
    int sum = (brown - 4) / 2;
    for (yRow = 1, yCol = sum - 1; yRow <= yCol; yRow++, yCol--) {
        if (yRow * yCol == yellow)
            break;
    }
    answer.push_back(yCol + 2);
    answer.push_back(yRow + 2);
    return answer;
}
