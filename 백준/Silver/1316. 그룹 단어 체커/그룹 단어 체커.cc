#include<iostream>
#include<string>
#include<vector>
#include<algorithm>
using namespace std;

void printv(vector<char> v) {
	for (int i = 0;i < v.size();i++) {
		cout << v[i] << " ";
	}
	cout << endl;
}


int main() {
	int n;	cin >> n;
	int answer = 0;
	for (int i = 0;i < n;i++) {
		string s;	cin >> s;
		vector<char> v;
		for (int i = 0;i < s.size();i++) {
			v.push_back(s[i]);
		}

		v.erase(unique(v.begin(), v.end()),v.end());
		int num = v.size();

		sort(v.begin(), v.end());

		v.erase(unique(v.begin(), v.end()),v.end());

		if (num == v.size()) {
			answer++;
		}
	}
	cout << answer++;
}