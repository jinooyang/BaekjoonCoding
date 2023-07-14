#include<iostream>
#include<vector>
#include<string>
using namespace std;


int main() {
	char matrix[5][15] ;
	for (int a = 0;a < 5;a++) {
		for (int b = 0;b < 15;b++) {
			matrix[a][b] = '*';
		}
	}
	int n[5];

	for (int j = 0;j < 5;j++) {
		string s;
		cin >> s;		
		for (int i = 0; i < s.length();i++) {
			matrix[j][i] = s[i];

		}
	}
	for (int i = 0;i < 15;i++) {
		for (int j = 0;j < 5;j++) {
			if(matrix[j][i]!='*')cout << matrix[j][i];
		}
	}
	
}
