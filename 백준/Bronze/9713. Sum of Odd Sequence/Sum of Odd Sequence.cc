#include<iostream>
using namespace std;


int main() {
	int c;cin >> c;
	for (int i = 0;i < c;i++) {
		int b;cin >> b;
		int sum = 0;
		for (int j = 1;j <= b;j++) {			
			if (j % 2 != 0) {
				sum = sum + j;
			}
		}
		cout << sum << endl;
		
	}
}