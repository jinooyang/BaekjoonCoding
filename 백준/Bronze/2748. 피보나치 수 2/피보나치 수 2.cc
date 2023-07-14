#include<iostream>
#include<vector>
#include<string>
using namespace std;


int main() {
	long long m[91];
	m[0] = 0;
	m[1] = 1;
	m[2] = 1;
	int n;cin >> n;
	for (int i = 3;i <= n;i++) {
		m[i] = m[i - 1] + m[i - 2];
	}


	cout << m[n];
}
