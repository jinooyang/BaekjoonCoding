#include<iostream>
using namespace std;

int f[41][3];//zero and one count

int fib(int n) {
	if (n == 0) {	
		//cout << 0;
		f[n][1] = 1;
		f[n][2] = 0;
		return 0;
	}
	else if(n==1){				
		//cout << 1;
		f[n][1] = 0;
		f[n][2] = 1;
		
		return 1;
	}
	else if (f[n][0] != -1)return f[n][0];
	else {	

		f[n][0] = fib(n - 1) + fib(n - 2);
		f[n][1] = f[n - 1][1] + f[n - 2][1];//zero
		f[n][2] = f[n - 1][2] + f[n - 2][2];//one
		return f[n][0];
	}	
}


int main() {
	int t;cin >> t;
	for (int i = 0;i < 41;i++) {
		f[i][0] = -1;
		f[i][1] = -1;
		f[i][2] = -1;
	}
	for (int i = 0;i < t;i++) {
		int n;cin >> n;
		fib(n);	
		cout << f[n][1] << " " << f[n][2] << "\n";
	}
	

}