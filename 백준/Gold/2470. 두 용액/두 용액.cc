#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;


int main() {
	int liq[100000];
	int n;	cin >> n;
	for (int i = 0;i < n;i++) {
		cin >> liq[i];
	}
	sort(liq, liq + n);
	int start = 0;
	int end = n - 1;
	int answer_a ;
	int answer_b ;
	int m = 2000000001;
	while (start < end) {
		int temp;
		if (liq[end] > 0 && liq[start] > 0) {//+ +
			temp = liq[end] + liq[start];
		}
		else temp = liq[end] - abs(liq[start]);//+ - , - - 
		if (abs(temp) < m) {
			m = abs(temp);
			answer_a = start;
			answer_b = end;
		}
		if (temp == 0)break;
		if (temp > 0) {
			end = end - 1;
		}
		if (temp < 0) {
			start = start + 1;
		}
	}
	//cout << m<<"\n";
	cout << liq[answer_a] << " " << liq[answer_b];
}