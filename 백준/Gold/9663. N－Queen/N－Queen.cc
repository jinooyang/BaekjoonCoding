#include<iostream>

using namespace std;

int n = 0;
int answer = 0;
int chess[15] = { 0 };
void printchess() {
	for (int i = 0;i < n;i++) {
		cout << chess[i];
	}
	cout << "\n";
}
bool chk(int k) {
	for (int i = 0; i < k; i++) {
		if (chess[i] == chess[k])return false;
		if (abs(chess[i] - chess[k]) == abs(i - k))return false;		
	}
	
	return true;
}

void rep(int a) {
	if (a == n) answer++;
	else {
		for (int i = 0;i < n;i++) {
			chess[a] = i;
			if (chk(a) == true) {
				rep(a + 1);
			}
		}
	}
}

int main() {
	cin >> n;
	
	rep(0);

	cout << answer;
}
