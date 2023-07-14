#include<iostream>
#include<stack>
#include<queue>
using namespace std;

int main() {	
	int array[101][101];
	int mark[101][101];
	for (int a = 0; a < 101;a++) {
		for (int b = 0; b < 101;b++) {
			mark[a][b] = 0;
		}
	}
	int n, m;

	cin >> n >> m;
	
	for (int i = 1;i <= n;i++) {
		//int temp;
		//cin >> temp;
		
		for (int j = 1;j <= m;j++) {
		//	array[i][j] = temp%10;
		//	temp = temp / 10;
			int num;
			scanf("%1d", &array[i][j]);
		}
	}
	
	/*for (int i = 1;i <= n;i++) { // check maze input
		for (int j = 1;j <=m;j++) {
			cout << array[i][j];
		}
		cout << endl;
	}*/

	queue<int> qi;
	queue<int> qj;
	queue<int> distance;

	qi.push(1); qj.push(1);	distance.push(1);
	
	int i = qi.front(); 
	int j = qj.front();
	int cnt = distance.front();
	while (true) {
		i = qi.front();		qi.pop();//큐에서 확인할 노드 꺼내오기
		j = qj.front();		qj.pop();//
		cnt = distance.front();	distance.pop();
	//up down right left into queue
	//left
		
		if (j > 1 && array[i][j - 1] != 0) { // 메이즈 안으로 범위 지정, 벽인지 확인
			if (mark[i][j - 1] == 0) { // 왔던길인지 확인하기
				qi.push(i); qj.push(j - 1); // 조건 만족하니 큐에 넣기
				distance.push(cnt + 1);
				mark[i][j - 1] = 1; // 큐에 넣었다고 마킹하기
				//cout << "left : " << cnt + 1;
				if (i == n && j - 1 == m) { break; } // 정답이면 끝내기
				
			}
		}
		//right
		if (j < m && array[i][j + 1] != 0) {
			if (mark[i][j + 1] == 0) {
				qi.push(i); qj.push(j + 1);
				distance.push(cnt + 1);
				mark[i][j + 1] = 1;
				//cout << "right : " << cnt + 1;
				if (i == n && j + 1 == m) { break; }
				
			}
		}
		//down
		if (i < n && array[i + 1][j] != 0) {
			if (mark[i + 1][j] == 0) {
				qi.push(i + 1); qj.push(j);
				distance.push(cnt + 1);
				mark[i + 1][j] = 1;
				//cout << "down : " << cnt + 1;
				if (i + 1 == n && j == m) { break; }
				
			}
		}
		//up
		if (i > 1 && array[i - 1][j] != 0) {
			if (mark[i - 1][j] == 0) {
				qi.push(i - 1); qj.push(j);
				distance.push(cnt + 1);
				mark[i - 1][j] = 1;
				//cout << "up : " << cnt + 1;
				if (i - 1 == n && j == m) { break; }
				
			}
		}
	}
	
	cout << distance.back();



}
