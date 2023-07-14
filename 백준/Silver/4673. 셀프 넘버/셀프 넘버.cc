#include<iostream>
using namespace std;


int d(int a) {
	int ans = a;
	while (a > 0) {
		ans += a % 10;
		a = a / 10;
	}
	return ans;
}


int main() {
	int chk[10001] = {false};
	for (int i = 1;i <= 10000;i++) {
		if(d(i)<=10000)chk[d(i)] = true;
	}
	for (int i = 1;i < 10001;i++) {
		if (chk[i] == false) {
			cout << i << "\n";
		}
	}
	return 0;
}