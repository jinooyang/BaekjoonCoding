#include<iostream>
#include<vector>
#include<string>
#include<algorithm>
using namespace std;


int main() {
	vector<int> score;
	int n;	cin >> n;
	int k;	cin >> k;
	for (int i = 0;i < n;i++) {
		int sc;cin >> sc;
		score.push_back(sc);
	}
	sort(score.begin(), score.end(),greater<int>());
	cout << score[k - 1];
}
