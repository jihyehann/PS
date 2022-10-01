#include <vector>

using namespace std;
int arr[1001][1001] = {0};
int solution(vector<vector<int>> board, vector<vector<int>> skill) {
    int answer = 0;

    for(vector<int> sk : skill) {
        int degree;
        if(sk[0] == 1) degree = -sk[5];
        else degree = sk[5];

        // 2차원 누적합
        arr[sk[1]][sk[2]] += degree;
        arr[sk[1]][sk[4]+1] -= degree;
        arr[sk[3]+1][sk[2]] -= degree;
        arr[sk[3]+1][sk[4]+1] += degree;
    }

    // 누적합 계산
    int n = board.size();
    int m = board[0].size();

    for(int j=0; j<m; j++)
        for (int i=1; i<n; i++)
            arr[i][j] += arr[i-1][j];
    for(int i=0; i<n; i++)
        for(int j=1; j<m; j++)
            arr[i][j] += arr[i][j-1];

    // 파괴되지 않은 건물 개수
    for(int i=0; i<n; i++)
        for(int j=0; j<m; j++)
            if(board[i][j] + arr[i][j] > 0) answer++;

    return answer;
}
