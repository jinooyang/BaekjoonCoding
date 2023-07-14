#include<iostream>

using namespace std;


int main() {
	int n;
	cin >> n;
	int f = 1;
	if (n == 0) { cout << 1;return 0; }
	for (int i = 1;i <= n;i++) {
		f = i * f;
	}
	cout << f;
}
