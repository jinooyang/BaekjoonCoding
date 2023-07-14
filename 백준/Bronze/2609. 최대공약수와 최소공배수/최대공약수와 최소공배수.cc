#include<iostream>
using namespace std;

int main() {
	int a, b;cin >> a >> b;

	int m = min(a, b);
	int mx = max(a, b);
	int gcd = 0;
	int lcm = 0;
	for (int i = 1;i <= m;i++) {
		if (a % i == 0 && b % i == 0)gcd = i;
	}
	int M = a * b;
	for (int i = mx;i <= M;i++) {
		if (i % a == 0 && i % b == 0) { lcm = i; break; }
	}
	cout << gcd << " " << lcm;
}