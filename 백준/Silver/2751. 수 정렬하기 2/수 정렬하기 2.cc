#include<iostream>
#include<vector>
#include<algorithm>
#include<string>

using namespace std;



int main() {	
    ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	
	int n;cin >> n;
	int a[1000001];

	for (int i = 0;i < n;i++) {
		//int c;
		cin >> a[i];
		//a.push_back(c);
	}

	sort(a, a+n);
	
	for (int i = 0;i < n;i++) {
		cout << a[i] << "\n";
	}
}
