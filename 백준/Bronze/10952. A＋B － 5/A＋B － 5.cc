#include<iostream>
using namespace std;

int main() {
	while (1) {
		int a;cin >> a;
		int b;cin >> b;
		if (a == 0 && b == 0)return 0;
		cout << a + b << "\n";
	}
}