#include<iostream>
#include<queue>
#include<set>
using namespace std;
int main() {
	int a, b; cin >> a >> b;

	queue<long long> q;
	queue<long long> qcnt;
	set<long long> s;

	q.push(a);
	qcnt.push(1);
	s.insert(a);
	long long answer = -1;
	while (true) {
		if (q.empty())break;
		long long num = q.front();
		long long cnt = qcnt.front();
		q.pop(); qcnt.pop();
		if (num == b) {
			answer = cnt;
			break;
		}

		//자식을 추가하기
		//set에 없으면


		if (s.find(num * 2) == s.end()) {
			if (2 * num <= b) {
				q.push(num * 2);
				qcnt.push(cnt + 1);
				s.insert(num * 2);
			}
		}
		if (s.find(num * 10 + 1) == s.end()) {
			if (num*10 + 1 <= b) {
				q.push(num * 10 + 1);
				qcnt.push(cnt + 1);
				s.insert(num * 10 + 1);
			}
		}

	}

	cout << answer;
	return 0;
}



