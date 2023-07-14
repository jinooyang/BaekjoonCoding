#include<iostream>

using namespace std;

int main() {
	int N, M;
	cin >> N >> M;
	int numbers[100];
	for (int i = 0;i < N;i++) {
		int temp;cin >> temp;
		numbers[i] = temp;
	}
	int max = 0;
	for (int i = 0;i < N - 2;i++) {
		for (int j = i + 1;j < N - 1;j++) {
			for (int k = j + 1;k < N;k++) {
				int sum = numbers[i] + numbers[j] + numbers[k];
				if (sum > max && sum <= M) max = sum;
			}
		}
	}
	cout << max;


}