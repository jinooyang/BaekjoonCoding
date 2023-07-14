#include<iostream>
using namespace std;


int main() {
	int n;
	cin >> n;
	for (int i = 0;i < n;i++) {
		int c;
		cin >> c;
		cout << "Pairs for " << c << ": ";
		for (int j = 1;j <= c / 2;j++) {
			
			if (j != c - j) {
				if (j != 1)cout << ", ";
				cout << j << " " << c - j;				
			}			
		}
		cout << endl;
	}
}