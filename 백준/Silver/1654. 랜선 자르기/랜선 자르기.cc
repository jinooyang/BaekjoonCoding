//1654

#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;
int main() {
	int n, k;cin >> n >> k;
	vector<long long> v;
	for (int i = 0;i < n;i++) {
		long long temp;	cin >> temp;
		v.push_back(temp);
	}
	sort(v.begin(), v.end());
	long long start = 1; long long end = v[v.size()-1];
	long long answer = 0;
	while (start <= end) {
		long long mid = (start + end) / 2;	
		int sum = 0;
		for (int i = 0;i < n;i++) {
			sum += v[i] / mid;
		}
		if (sum >= k) {
			start = mid + 1;
			if (mid > answer)answer = mid;
		}
		else {
			end = mid - 1;
		}
	}
	cout << answer;
}