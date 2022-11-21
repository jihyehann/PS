#include <iostream>
using namespace std;

int n, l;
int map[101][101];

int rowCount() {
    int cnt = 0;
    for (int i=0; i<n; i++) {
        int prev = map[i][0];
        bool pass = true;
        bool check[101] = {false};
        for (int j=1; j<n; j++) {
            if (prev - map[i][j] > 1 || prev-map[i][j] < -1) {
                pass = false; break;
            }
            if (prev > map[i][j]) {
                if (n-j < l) {
                    pass = false; break;
                }
                check[j] = true;
                for (int k=1; k<l; k++) {
                    if (map[i][j] != map[i][j+1] || check[j+1]) {
                        pass = false; break;
                    }
                    check[j+1] = true;
                    j++;
                }
            } else if (prev < map[i][j]) {
                if (j < l) {
                    pass = false; break;
                }
                for (int k=1; k<=l; k++) {
                    if (prev != map[i][j-k] || check[j-k]) {
                        pass = false; break;
                    }
                    check[j-k] = true;
                }
            }
            prev = map[i][j];
        }

        if (pass) {
            cnt++;
        }
    }
    return cnt;
}

int colCount() {
    int cnt = 0;
    for (int j=0; j<n; j++) {
        int prev = map[0][j];
        bool pass = true;
        bool check[101] = {false};
        for (int i=1; i<n; i++) {
            if (prev - map[i][j] > 1 || prev-map[i][j] < -1) {
                pass = false; break;
            }
            if (prev > map[i][j]) {
                if (n-i < l) {
                    pass = false; break;
                }
                check[i] = true;
                for (int k=1; k<l; k++) {
                    if (map[i][j] != map[i+1][j] || check[i+1]) {
                        pass = false; break;
                    }
                    check[i+1] = true;
                    i++;
                }

            } else if (prev < map[i][j]) {
                if (i < l) {
                    pass = false; break;
                }
                for (int k=1; k<=l; k++) {
                    if (prev != map[i-k][j] || check[i-k]) {
                        pass = false; break;
                    }
                    check[i-k] = true;
                }
            }
            prev = map[i][j];
        }

        if (pass) {
            cnt++;
        }
    }
    return cnt;
}

int main() {
    int answer = 0;

    cin >> n >> l;
    for (int i=0; i<n; i++) {
        for (int j=0; j<n; j++) {
            cin >> map[i][j];
        }
    }

    // 가로 검사
    answer += rowCount();

    // 세로 검사
    answer += colCount();

    cout << answer << endl;
}

