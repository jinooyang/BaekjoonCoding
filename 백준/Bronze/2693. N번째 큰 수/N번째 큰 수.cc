#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;


int main() {
	int c;cin >> c;
	for (int i = 0;i < c;i++) {
		vector<int> a;
		for (int j = 0;j < 10;j++) {
			int b;cin >> b;
			a.push_back(b);
		}
		sort(a.begin(), a.end(), greater<int>());
		a.erase(a.begin());
		a.erase(a.begin());
		cout << a[0] << endl;
	}
}