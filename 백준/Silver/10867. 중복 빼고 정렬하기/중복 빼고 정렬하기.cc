#include<iostream>
using namespace std;
#include<vector>
#include<algorithm>

vector<int> a;

int main() {
	int n, k;
	cin >> n;
	for (int i = 0;i < n;i++) {
		cin >> k;
		a.push_back(k);
	}
	sort(a.begin(), a.end());
	a.erase(unique(a.begin(), a.end()), a.end());

	for (int i = 0;i < a.size();i++) {
		cout << a[i] << " ";
	}
}