#include<iostream>
using namespace std;

int main() {
	//500 100 50 10 5 1
	//1000 지폐를 한장 냈을 때 잔돈은?

	int n; cin >> n;
	int change = 1000 - n;
	int coin[6] = { 500,100,50,10,5,1 };
	int i = 0;
	int cnt = 0;
	while (change>0) {
		if (change - coin[i] >= 0) {
			change = change - coin[i];
			cnt++;
		}
		else {
			i++;
		}
		
	}
	cout << cnt;
}