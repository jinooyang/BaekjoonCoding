#include<iostream>
#include<vector>
#include<algorithm>
#include<string>

using namespace std;
bool isPrime(int a) {
	if (a == 1) return false;
	
	for (int i = 2;i < a;i++) {
		if (a % i == 0) return false;
	}
	return true;
}

int main() {	
	int n;cin >> n;
	int cnt = 0;
	for (int i = 0;i < n;i++) {
		int a;cin >> a;
		if (isPrime(a) == true) cnt++;
	}
	cout << cnt;
}
