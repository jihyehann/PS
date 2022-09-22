#include <string>
#include <vector>
#include <map>
using namespace std;

string solution(vector<string> survey, vector<int> choices) {
    int size = survey.size();
    map<char, int> type;
    for (int i = 0; i < size; i++) {
        string sur = survey[i];
        int choice = choices[i];
        if(choice < 4) {
            type[sur[0]] += (4-choice);
        }
        else if (choice > 4) {
            type[sur[1]] += (choice-4);
        }
    }

    string answer = "";
    answer += type['R'] >= type['T'] ? 'R' : 'T';
    answer += type['C'] >= type['F'] ? 'C' : 'F';
    answer += type['J'] >= type['M'] ? 'J' : 'M';
    answer += type['A'] >= type['N'] ? 'A' : 'N';
    return answer;
}
