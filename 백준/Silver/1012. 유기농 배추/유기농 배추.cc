#include<iostream>
#include<vector>
#include<queue>
#include<string>
using namespace std;
void printmap(int across, int down, int  map[25][25]) {
	for (int i = 0;i < down;i++) {
		for (int j = 0;j < across;j++) {
			cout << map[i][j];
		}
		cout << endl;
	}
	cout << "\n";
}
int main() {
	int testcase;
	cin >> testcase;
	for (int i = 0;i < testcase; i++) {
		//input
		int across, down, num;
		cin >> across >> down >> num;
		int answer = 0;
		int map[2500][2500] = { 0, };//25002500
		bool check[2500][2500] = { 0, };//25002500
		for (int j = 0;j < num;j++) {//number of cabbage
			int a, b;//across down
			cin >> a >> b;
			map[b][a] = 1;
		}//map draw complete
		
		
		//bfs
		queue<int> qx;
		queue<int> qy;
		while (true) {
			for (int y = 0;y < down;y++) {//find 1 and push into queue
				for (int x = 0;x < across;x++) {
					if (map[y][x] == 1) {
						qx.push(x);qy.push(y);
						//cout << "x:" << x << "  y:" << y << "\n";
						break;
					}
				}
				if (qx.empty() != true)break;
			}
			
			if (qx.empty() != true) answer++;
			else break;
			//printmap(across, down, map);

			while (true) {
				if (qx.empty() == true) break;
				int xx, yy;
				xx = qx.front(); yy = qy.front();
				qx.pop();qy.pop();
				if (map[yy][xx] == 1) { map[yy][xx] = 0; }
				//up y-1
				if (yy - 1 >= 0) {
					if (check[yy-1][xx] != true) {
						if (map[yy-1][xx] == 1) {
							qx.push(xx);qy.push(yy - 1);check[yy-1][xx] = true;
						}//if 1
					}
				}
				//down y+1
				if (yy + 1 < down) {
					if (check[yy+1][xx] != true) {
						if (map[yy+1][xx] == 1) {
							qx.push(xx);qy.push(yy + 1);check[yy+1][xx] = true;
						}
					}
				}

				//left x-1
				if (xx - 1 >= 0) {
					if (check[yy][xx-1] != true) {
						if (map[yy][xx-1] == 1) {
							qx.push(xx - 1);qy.push(yy);check[yy][xx - 1] = true;
						}
					}
				}
				//right x+1
				if (xx + 1 < across) {
					if (check[yy][xx+1] != true) {
						if (map[yy][xx + 1] == 1) {
							qx.push(xx + 1);qy.push(yy);check[yy][xx + 1] = true;
						}

					}
				}
			}
		}		
		cout << answer<<"\n";
	}
}
		

