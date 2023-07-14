#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main() {
	int n;
	cin >> n;
	vector<int> v;
	for (int i = 0;i < n;i++) {
		int temp;cin >> temp;
		v.push_back(temp);
	}
	sort(v.begin(),v.end(),greater<int>());
	int max = 0;
	int k = 1;
	for (int i = 0;i < v.size();i++) {
		int tempmax = v[i] * k;
		if (max < tempmax) max = tempmax;
		k++;
	}
	cout << max;
}