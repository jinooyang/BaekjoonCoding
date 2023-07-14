#include<iostream>
#include<vector>
#include<string>
#include<algorithm>
#include<queue>
using namespace std;


int main() {
	int tmt[1000][1000];
	bool btmt[1000][1000];
	int n, m;cin >> n >> m;
	queue<int> qi, qj, qcnt;

	for (int i = 0;i < m;i++) {
		for (int j = 0;j < n;j++) {
			int c;cin >> c;
			tmt[i][j] = c;
			if (c == 1) {
				qi.push(i); qj.push(j);	qcnt.push(0);
				btmt[i][j] = true;
			}
		}
	}

	int max = 0;

	while (true) {
		if (qi.empty() == true)break;
		int i, j, cnt;
		i = qi.front(); j = qj.front(); cnt = qcnt.front();
		if (cnt > max) { max = cnt; }


		qi.pop();qj.pop();qcnt.pop();

		//up	i-1
		if (i - 1 >= 0) {//inside box
			if (tmt[i - 1][j] == 0) {//unripe tomatoe
				if (btmt[i - 1][j] != true) {//check X				
					btmt[i - 1][j] = true;
					tmt[i - 1][j] = 1;
					qi.push(i - 1);qj.push(j);qcnt.push(cnt + 1);
				}
			}
		}
		//down	i+1
		if (i + 1 < m) {
			if (tmt[i + 1][j] == 0) {
				if (btmt[i + 1][j] != true) {
					btmt[i + 1][j] = true;
					tmt[i + 1][j] = 1;
					qi.push(i + 1);qj.push(j);qcnt.push(cnt + 1);
				}
			}
		}
		//left	j-1
		if (j - 1 >= 0) {
			if (tmt[i][j - 1] == 0) {
				if (btmt[i][j - 1] != true) {
					btmt[i][j - 1] = true;
					tmt[i][j - 1] = 1;
					qi.push(i);qj.push(j - 1);qcnt.push(cnt + 1);
				}
			}
		}
		//right	j+1
		if (j + 1 < n) {
			if (tmt[i][j + 1] == 0) {
				if (btmt[i][j + 1] != true) {
					btmt[i][j + 1] = true;
					tmt[i][j + 1] = 1;
					qi.push(i);qj.push(j + 1);qcnt.push(cnt + 1);
				}
			}
		}

	}//bfs finish
	for (int i = 0;i < m;i++) {
		for (int j = 0;j < n;j++) {			
			if (tmt[i][j] == 0) {
				max = -1;
			}
		}
		
	}

	cout << max;
}
