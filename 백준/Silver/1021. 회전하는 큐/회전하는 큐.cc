#include<iostream>
#include<deque>
using namespace std;
deque<int> dq;
int answer = 0;
int location[50];
void func1() {
	dq.pop_front();
}
void func2() {//left
	int temp = dq.front();
	dq.pop_front();
	dq.push_back(temp);
}
void func3() {//right
	int temp = dq.back();
	dq.pop_back();
	dq.push_front(temp);
}
/*void printdq() {
	for (int i = 0;i < dq.size();i++) {
		cout << dq[i];
	}
	cout << "\n";
}*/
int main() {
	int N, M;
	cin >> N >> M;	
	for (int i = 1;i <=N;i++) {		
		dq.push_back(i);
	}
	//printdq();
	for (int i = 0;i < M;i++) {
		int temp; cin >> temp;
		if (dq.front() == temp) {
			func1(); 
			//cout << "func1() : ";
			//printdq();
		}
		else {
			int index = 0;
			for (int j = 0;j < dq.size();j++) {
				if (dq[j] == temp) { index = j;	break; }
			}
			if (dq.size() - index > index) {
				while (true) {
					func2();
					answer++;
					//cout << "func2() : ";
					//printdq();
					if (dq.front() == temp) { func1();	break; }
				}
			}
			else {
				while (true) {
					func3();
					answer++;
					//cout << "func3() : ";
					//printdq();
					if (dq.front() == temp) { func1();	break; }
				}
			}


		}
	}
	cout << answer;

}