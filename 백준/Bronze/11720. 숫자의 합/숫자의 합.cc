#include<iostream>
#include<string>
using namespace std;

int main() {
	int n;	cin >> n;
	string s;	cin >> s;
	int answer = 0;
	for (int i = 0;i < n;i++) {
		answer += int(s[i]-'0');
	}
	cout << answer;
}