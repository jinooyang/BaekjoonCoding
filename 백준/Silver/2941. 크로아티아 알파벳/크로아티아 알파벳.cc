#include<string>
#include<iostream>
#include<vector>
using namespace std;
int main() {
	string word;cin >> word;
	int answer = 0;
	for (int i = 0;i < word.size();i++) {
		char now = word[i];
		//string chk = "" + now;
		//c일경우
		if (now == 'c') {
			if (i + 1 < word.size() && (word[i + 1] == '=' || word[i + 1] == '-'))
				i = i + 1;
		}
		//d일경우
		else if (now == 'd') {
			if (i + 1 < word.size() && word[i + 1] == '-') {
				i = i + 1;
			}
			else if (i + 2 < word.size() && word[i + 1] == 'z' && word[i + 2] == '=') {
				i = i + 2;
			}
		}
	
		//l일경우
		else if (now == 'l') {
			if (i + 1 < word.size() && word[i + 1] == 'j') {
				i = i + 1;
			}
		}
		//n일경우
		else if (now == 'n') {
			if (i + 1 < word.size() && word[i + 1] == 'j') {
				i = i + 1;
			}
		}
		//s일경우
		else if (now == 's') {
			if (i + 1 < word.size() && word[i + 1] == '=') {
				i = i + 1;
			}
		}
		//z일경우
		else if (now == 'z') {
			if (i + 1 < word.size() && word[i + 1] == '=') {
				i = i + 1;
			}
		}
		answer++;
		//cout << chk << "\n";
	}
	cout << answer;
	

}