#include<iostream>
using namespace std;

bool chk(int num) {
	int a, b, c;
	c = num % 10;
	num = num / 10;
	b = num % 10;
	num = num / 10;
	a = num % 10;
	if (a - b == b - c)return true;
	else return false;
}

int main() {
	int n;cin >> n;
	if (n == 1000)n = 999;
	if (n >= 1 && n <= 99) {
		cout << n;
		return 0;
	}
	int ans = 99;
	for (int i = 100;i <= n;i++) {
		if (chk(i) == true)ans++;
	}
	cout << ans;
}