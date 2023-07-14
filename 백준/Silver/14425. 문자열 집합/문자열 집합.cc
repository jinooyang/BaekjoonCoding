#include<map>
#include<iostream>
#include<string>
#include<vector>
#include<algorithm>
using namespace std;

int main() {
	int a, b;
	cin >> a>> b;
	map<string, int> m;
	for (int i = 0; i < a; i++) {
		string temp;
		cin >> temp;
		m[temp]++;
	}
	int answer = 0;
	for (int i = 0; i < b; i++) {
		string temp;
		cin >> temp;
		if (m[temp] >0) answer++;
	}
	cout << answer;
}