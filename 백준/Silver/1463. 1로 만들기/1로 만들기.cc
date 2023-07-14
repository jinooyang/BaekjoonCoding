#include<iostream>
#include<vector>
#include<string>
using namespace std;


int main() {
	vector<int>m;
	for (int i = 0;i <= 1000001;i++) {
		m.push_back(1000000);
	}
	int n;cin >> n;
	m[0] = -1;
	m[1] = 0;

	for (int i = 1;i <= n;i++) {
		if (i + 1 <= 1000000) { 
			if (m[i + 1] > m[i] + 1)m[i + 1] = m[i] + 1; 
		}
		if (i * 3 <= 1000000) {
			if (m[i * 3] > m[i] + 1)m[i * 3] = m[i] + 1; 
		}
		if (i * 2 <= 1000000) {
			if (m[i * 2] > m[i] + 1)m[i * 2] = m[i] + 1; 
		}
	}
	cout << m[n];
}
