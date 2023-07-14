#include<iostream>
#include<string>
#include<stack>
using namespace std;

int main() {
	stack<int> s;
	int k;cin >> k;
	for (int i = 0;i < k;i++) {
		int temp;cin >> temp;
		if (temp == 0) s.pop();
		else s.push(temp);
	
	}

	if (s.empty()) cout << 0;
	else {
		int sum = 0;
		while (!s.empty()) {
			sum += s.top();
			s.pop();
		}
		cout << sum;
	}


}