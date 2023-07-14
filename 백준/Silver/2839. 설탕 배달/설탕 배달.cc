#include<iostream>
#include<vector>
#include<string>
using namespace std;


int main() {
	int n;cin >> n;
	int m[5001];
	for (int i = 0;i < 5001;i++) {
		m[i] = 2000;
	}

	m[3] = 1;
	m[5] = 1;

	for (int i = 3;i < 5001;i++) {
		if (m[i + 5]>m[i]+1) {
			m[i + 5] = m[i] + 1; 
		}
		if (m[i + 3]>m[i]+1) {
			m[i + 3] = m[i] + 1; 
		}
	}
	if (m[n] == 2000)cout << -1;
	else cout << m[n];
	
}
