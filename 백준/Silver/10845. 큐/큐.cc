#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>
using namespace std;
queue<int> q;
void push() {
	int b;cin >> b;
	q.push(b);
}
void pop() {
	if (q.empty() == true) cout << "-1" << endl;
	else {
		cout << q.front() << endl;
		q.pop();
	}
}
void q_size() {
	cout << q.size() << endl;
}
void q_empty() {
	if (q.empty() == true)cout << "1" << endl;
	else cout << "0" << endl;
}
void q_front() {
	if (q.empty() == true) cout << "-1" << endl;
	else cout << q.front() << endl;
}
void q_back() {
	if (q.empty() == true) cout << "-1" << endl;
	else cout << q.back() << endl;
}
int main() {
	int n;cin >> n;


	for (int i = 0;i < n;i++) {
		string a;cin >> a;
		if (a == "push") {
			push();
		}
		else if (a == "pop") {
			pop();
		}
		else if (a == "size") {
			q_size();
		}
		else if (a == "empty") {
			q_empty();
		}
		else if (a == "front") {
			q_front();
		}
		else if (a == "back") {
			q_back();
		}
	}
}