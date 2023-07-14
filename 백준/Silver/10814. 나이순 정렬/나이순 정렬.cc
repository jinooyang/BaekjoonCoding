#include<vector>
#include<iostream>
#include<algorithm>
#include<string>
using namespace std;

int main() {

	int n;cin >> n;
	vector<pair<int , pair<int, string>>> v;

	for (int i = 0;i < n;i++) {
		int tempi;string temps;
		cin >> tempi >> temps;
		v.push_back(make_pair(tempi,(make_pair(i,temps))));
	}
	sort(v.begin(), v.end());
	for (int i = 0;i < n;i++) {
		cout << v[i].first << " " << v[i].second.second << "\n";
	}

}