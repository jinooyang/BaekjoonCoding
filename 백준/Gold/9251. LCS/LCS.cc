#include<iostream>
#include<string>

using namespace std;

int LCS[1001][1001];

int main() {
	string s1, s2;
	cin >> s1 >> s2;
	for (int i = 0;i <= s2.size();i++) {
		LCS[i][0] = 0;
	}
	for (int j = 0;j <= s1.size();j++) {
		LCS[0][j] = 0;
	}
	for (int i = 0;i < s2.size();i++) {
		for (int j = 0;j < s1.size();j++) {
			int temp = max(LCS[i+1][j], LCS[i][j + 1]);
			temp = max(LCS[i][j] + (s1[j] == s2[i]), temp);
			LCS[i + 1][j + 1] = temp ;
		}
	}
	cout << LCS[s2.size()][s1.size()];

} 