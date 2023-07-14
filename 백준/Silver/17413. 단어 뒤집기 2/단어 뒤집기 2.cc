#include<iostream>
#include<string>
#include<stack>
#include<queue>
#include<vector>
#include <sstream>
using namespace std;

int main() {
	string str;
	getline(cin, str);
;
	string answer = "";

	bool isTag = false;
	stack<char> stk;
	for (int i = 0;i < str.size();i++) {
		if (str[i] == '<')isTag = true;
		if (str[i] == ' '){
			answer += str[i];
			continue;
		}
		if (isTag) {//answer에 바로 추가
			answer += str[i];
		}
		else {//stack에 넣고, 다음문자가 '<'이거나 ' '이거나 문자열의 끝일경우  answer에 추가
			stk.push(str[i]);
			if (i == str.size() - 1 || str[i + 1] == '<' || str[i + 1] == ' ') {
				while (!stk.empty()) {
					answer += stk.top();
					stk.pop();
				
				}
			}
		}
		if (str[i] == '>') isTag = false;
	}
	cout << answer;
	





}