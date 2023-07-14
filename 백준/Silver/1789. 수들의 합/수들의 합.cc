#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;


int main() {
	long long s;cin >> s;
	long long sum = 0;

	for (int i = 1;i <= s;i++) {
		sum += i;
		if (sum>s) {
			cout<< i -1 ;
			return 0;
		}
		if (sum == s) {
			cout << i ;
			return 0;
		}
	}
}