/******************************************************************************

                              Online C++ Compiler.
               Code, Compile, Run and Debug C++ program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
int m2[999][999];
int main() {
	int a, b;cin >> a >> b;
	int xx,yy;

	int num = 1;
	int x = a / 2;
	int y = a / 2;
	m2[x][y] = num;
	num++;
	int tracki = 0;
	for (int i = 1;i <a ;i=i+2) {
		tracki = i;
		for (int k = 0; k < i;k++) {//up
			x = x - 1;
			m2[x][y] = num;
			num++;
		}
		for (int k = 0; k < i;k++) {//right
			y = y + 1;
			m2[x][y] = num;
			num++;
		}
		for (int k = 0; k < i+1;k++) {//down
			x = x + 1;
			m2[x][y] = num;
			num++;
		}
		for (int k = 0; k < i + 1;k++) {//left
			y = y - 1;
			m2[x][y] = num;
			num++;
		}
	}
	for (int k = 0; k < tracki+1;k++) {//up
		x = x - 1;
		m2[x][y] = num;
		num++;
	}
	for (int q = 0;q < a;q++) {
		for (int p = 0;p < a;p++) {
			cout << m2[q][p] << " ";
			if(m2[q][p]==b){xx=q;yy=p;}
		}
		cout << endl;
	}
	cout<<xx+1<<" "<<yy+1;

}