#include <string>
#include <vector>
#include <algorithm>
#include <sstream>
#include <cmath>

using namespace std;

string convert(int n, int k) {
    string ans = "";
    while(n > 0) {
        ans = ans + to_string(n % k) ;
        n /= k;
    }
    reverse(ans.begin(), ans.end());
    return ans;
}

bool isPrime(long long num) {
    if(num<2) return false;
    for(int i=2; i<=sqrt(num); i++) {
        if(num%i == 0) return false;
    }
    return true;
}

int findPrime(string input) {
    int ans = 0;
    vector<string> num;
    stringstream ss(input);
    string temp;
    while(getline(ss, temp, '0')) {
        if(temp.length() > 0)
            num.push_back(temp);
    }
    for(auto iter=num.begin(); iter!=num.end(); iter++){
        if((isPrime(stoll(*iter)))) ans++;
    }
    return ans;
}

int solution(int n, int k) {
    string input = convert(n, k);
    return findPrime(input);
}
