#include<iostream>
using namespace std;


int main() {
	long a, b;
	cin >> a >> b;
	if (b > a) {
		cout << b - a - 1 << endl;
		for (int i = a + 1;i < b;i++) {
			cout << i << " ";
		}
	}
	else if (a > b) {
		cout << a - b - 1 << endl;
		for (int i = b + 1;i < a;i++) {
			cout << i << " ";
		}
	}
	else cout << 0;
	return 0;
}