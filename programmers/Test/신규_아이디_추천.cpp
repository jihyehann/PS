#include <bits/stdc++.h>
#include <string>
#include <vector>
using namespace std;

string solution(string new_id) {
    string answer = "";
    
    for(int i=0; i<new_id.length(); i++) {
        char c = new_id[i];
        if (isalpha(c)) {new_id[i] = (char) tolower(c);}
        else if (isdigit(c) || c == '-' || c == '_' || c == '.') continue;  
        else { new_id.erase(i, 1); i--; }
    }

    for(int i=0; i<new_id.length(); i++) {
        char c = new_id[i];
        if(c == '.') {
            if (i != new_id.length()-1 && new_id[i+1] == '.') { new_id.erase(i,1); i--; }
            else if (i == 0 || i == new_id.length()-1) { new_id.erase(i,1); i--; }
        }
    }

    int len = new_id.length();

    if(len >= 16) {
        new_id = new_id.substr(0, 15);
        while(true) {
            if(new_id.back() == '.') new_id.pop_back();
            else break;
        }
        if(new_id.length() > 2) return new_id;
    }

    if(new_id.length() == 0) return "aaa";
    if(new_id.length() == 1) return new_id+new_id[0]+new_id[0];
    if(new_id.length() == 2) return new_id+new_id[1];

    return new_id;
}
