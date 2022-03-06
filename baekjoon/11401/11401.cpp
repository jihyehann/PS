#include <iostream>
#define MOD 1000000007
using namespace std;
long long arr[4000001];

long long calc(long long x, int y) {
    if(y == 0) return 1;
    if(y == 1) return x;
    if (y % 2 == 1) {
        return x * calc(x, y-1) % MOD;
    }
    else {
        long long temp = calc(x, y / 2);
        return temp * temp % MOD;
    }
}

int main() {
    int n, k;
    cin >> n >> k;

    arr[0] = arr[1] = 1;
    for(int i=2; i<=n; i++) {
        arr[i] = arr[i-1] * i % MOD;
    }

    // 페르마의 소정리 이용
    // 1/(k! * (n-k)!) % MOD 계산
    long long temp = calc(arr[k] * arr[n-k] % MOD, MOD-2) % MOD;

    // nCk = n!/{k!(n-k)!} % MOD
    cout << arr[n] * temp % MOD << endl;

    return 0;
}
