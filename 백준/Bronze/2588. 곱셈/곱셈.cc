#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;


int main() {
	int a, b;cin >> a >> b;
	int ab = a * b;
	int x = b % 10;
	b = b - x;
	int y = b % 100;
	b = b - y;
	int z = b % 1000;
	cout << a * x << endl;
	cout << a * (y/10) << endl;
	cout << a * (z/100) << endl;
	cout << (ab) << endl;



}