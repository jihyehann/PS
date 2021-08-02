#include <string>
#include <vector>
using namespace std;
int num[10] = {0, };
int ans = 0;

bool isPrime(int num) {
    for(int i=2; i*i <= num; i++)
        if(num % i == 0) return false;
    return true;
}

void make(string now, int arr[10]) {
    int copy[10];
    int last = now.back()-'0';
    for(int i=0; i<10; i++)
        copy[i] = arr[i];
    copy[last]--;

    int nowNum = 0;
    for(int i=0; i<now.length(); i++)
        nowNum = nowNum * 10 + (now.at(i)-'0');

    if(nowNum >= 2 && isPrime(nowNum))
        ans++;

    for(int i=0; i<10; i++){
        if(copy[i] > 0)
            make(now + char(i+'0'), copy);
    }
}

int solution(string numbers) {
    int len = numbers.length();
    for(int i=0; i<len; i++) {
        int n = numbers.at(i)-'0';
        num[n]++;
    }

    for(int i=1; i<10; i++){
        if(num[i] < 1) continue;
        string tmp;
        make(tmp+char(i+'0'), num);
    }
    return ans;
}
