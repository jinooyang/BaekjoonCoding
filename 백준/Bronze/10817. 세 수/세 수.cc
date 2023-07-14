#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main() {
	vector<int> a;
	for (int i = 0; i < 3; i++) {
		int temp; cin >> temp;
		a.push_back(temp);
	}
	sort(a.begin(), a.end());
	cout << a[1];
	
}