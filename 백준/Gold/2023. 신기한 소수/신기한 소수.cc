#include<iostream>
#include<vector>
#include<algorithm>
#include<cmath>
using namespace std;

bool isPrime(int n) {
	if (n < 2) return false;
	int a = (int)sqrt(n);
	for (int i = 2; i <= a; i++) if (n % i == 0) return false;
	return true;
}


int main() {
	
	vector<int> answer;

	int n;cin >> n;

	vector<int> a;
	a.push_back(2);
	a.push_back(3);
	a.push_back(5);
	a.push_back(7);

	

	for (int i = 0;i < n-1;i++) {//i==0 -> 20 30 50 70 i==6 -> 8digit number
		vector<int> temp;
		for (int u = 0;u < a.size();u++) {
			a[u] = a[u] * 10;			
			for (int j = 0;j < 10;j++) {
				if (isPrime(a[u] + j)) {
					temp.push_back(a[u] + j);
				}
			}			

		}
		//new a
		int asize = a.size();
		for (int k = 0;k < asize;k++) {
			a.erase(a.begin());
		}
		for (int k = 0;k < temp.size();k++) {
			a.push_back(temp[k]);
		}
	}

	sort(a.begin(), a.end());

	for (int i = 0;i < a.size();i++) {
		cout << a[i] << "\n";
	}
}