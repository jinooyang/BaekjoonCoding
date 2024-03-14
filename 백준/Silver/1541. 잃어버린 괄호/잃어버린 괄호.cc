#include<string>
#include<iostream>
#include<vector>
using namespace std;

int main() {

	string s;
	cin >> s;
	
	vector<int> vi;
	vector<char> vc;
	int answer = 0;
	int temp = 0;
	for (int i = 0;i < s.size();i++) {	//숫자는 int로 저장하고 기호는 char로 저장한다	
		if (s[i] == '+') {//+
			vc.push_back('+');
			vi.push_back(temp);
			temp = 0;
		}
		else if (s[i] == '-') {//-
			vc.push_back('-');
			vi.push_back(temp);
			temp = 0;
		}
		else {//number
			temp = temp * 10 + (int)(s[i]-'0');
		}	
	}
	vi.push_back(temp);


	bool isMinus = false;
	answer = vi[0];
	for (int i = 0;i < vc.size();i++) {		
		if (vc[i] == '-') isMinus = true;
		if (isMinus == false) {
			answer = answer + vi[i+1];
		}
		else answer = answer - vi[i+1];
	}


	cout << answer;
}