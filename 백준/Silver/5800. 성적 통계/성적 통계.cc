#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;


int main() {
	int n;cin >> n;//class#
	for (int i = 0;i < n;i++) {
		int c;cin >> c;//student#
		vector<int> score;
		for (int j = 0;j < c;j++) {
			int s;cin >> s;//score
			score.push_back(s);
		}
		sort(score.begin(), score.end());
		int gap = 0;
		for (int j = 0;j < c - 1;j++) {
			gap = max(gap, score[j + 1] - score[j]);
		}
		cout << "Class " << i+1 << endl;
		cout << "Max " << score[c-1] << ", Min " << score[0] << ", Largest gap " << gap << endl;
	}
}