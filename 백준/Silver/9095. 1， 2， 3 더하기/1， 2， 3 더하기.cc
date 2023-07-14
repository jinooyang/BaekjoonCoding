#include<iostream>
using namespace std;

int ans[11] = { 0 };
int f(int n) {
	if (n == 1)return 1;
	if (n == 2)return 2;
	if (n == 3)return 4;
	if (ans[n-1] != 0)return ans[n-1];
	/*
	f(1) = 1 = 1
	f(2) = 1+1, 2 = 2
	f(3) = 1+1+1, 2+1, 1+2, 3 = 4
	f(4) = 1+1+1+1, 2+1+1(3), 3+1(2), 2+2 = 7
	*/
	ans[n-1] = f(n - 1) + f(n - 2) + f(n - 3);
	return ans[n-1];
}

int main() {

	int t;cin >> t;
	for (int i = 0;i < t;i++) {
		int n;cin >> n;
		cout<<f(n)<<"\n";
		
	}
}