#include<iostream>
using namespace std;

int main() {
	int n;cin >> n;
	int s;cin >> s;
	int numbers[20];
	int answer = 0;
	for (int i = 0;i < n;i++) {
		cin >> numbers[i];
	}
	for (int i = 1;i < (1<<n);i++) {
		int sum = 0;
		for (int j = 0;j < n;j++) {
			
			if (i & (1 << j))sum += numbers[j];
		}
		if (sum == s) answer++;
	}
	cout << answer;
}