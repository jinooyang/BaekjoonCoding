#include<iostream>

#include<vector>
using namespace std;

int main() {
	int n;cin >> n;
	int men[50] = {0};
	vector<pair<int, int>> v;
	for (int i = 0;i < n;i++) {
		int a, b;cin >> a >> b;
		v.push_back(make_pair(a, b));
	}

	for (int i = 0;i < n;i++) {
		int temp = 0;
		for (int j = 0;j < n;j++) {
			if (v[i].first < v[j].first && v[i].second < v[j].second)temp++;
		}
		cout << temp+1 << " ";
	}

}