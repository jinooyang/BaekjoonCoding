#include<iostream>
#include<queue>
#include<algorithm>
#include<vector>
using namespace std;

void printmap(int across, int down, int map[8][8]) {
	for (int i = 0;i < down;i++) {
		for (int j = 0;j < across;j++) {
			cout << map[i][j]<<" ";
		}
		cout << "\n";
	}
}


int main() {
	int across, down;
	cin >> down >> across;
	int originalmap[8][8];
	int map[8][8];
	int wall[8][8];
	vector<pair<int, int>> emptySpace;
	vector < pair<int, int>> virus;
	int max = 0;
	int delti[4] = { -1,1,0,0 };
	int deltj[4] = { 0,0,-1,1 };
	int original_zero = 0;
	for (int i = 0;i < down;i++) {
		for (int j = 0;j < across;j++) {
			int temp; cin >> temp;
			originalmap[i][j] = temp;
			
			if (temp == 0) { 
				original_zero++;	
				emptySpace.push_back(make_pair(i, j));
			}
			if (temp == 2) { 
				virus.push_back(make_pair(i, j));
			}
		}
	}
	//cout << original_zero << endl;
	//printmap(across, down, originalmap);
	for (int a = 0;a < emptySpace.size();a++) {

		for (int b = a + 1;b < emptySpace.size();b++) {

			for (int c = b + 1;c < emptySpace.size();c++) {

				bool check[8][8];
				for (int c = 0;c < down;c++) {
					for (int cc = 0;cc < across;cc++) {
						check[c][cc] = false;
						map[c][cc] = originalmap[c][cc];
					}
				}
				//printmap(across, down, map);
				int tempzero = original_zero;
				map[emptySpace[a].first][emptySpace[a].second] = 1;//3개를 뽑아서 벽을 세운다 모든 경우의 수
				tempzero--;
				map[emptySpace[b].first][emptySpace[b].second] = 1;
				tempzero--;
				map[emptySpace[c].first][emptySpace[c].second] = 1;
				tempzero--;
				//cout << "wall one: " << emptySpace[a].first << " " << emptySpace[a].second << "\n";
				//cout << "wall two: " << emptySpace[b].first << " " << emptySpace[b].second << "\n";
				//cout << "wall three: " << emptySpace[c].first << " " << emptySpace[c].second << "\n";
				//바이러스 위치에서 bfs돌리고 빈칸 체크한다
				queue<int>qi;//make queue
				queue<int>qj;
				for (int i = 0;i < virus.size();i++) {//add virus to queue
					qi.push(virus[i].first);
					qj.push(virus[i].second);
					check[virus[i].first][virus[i].second] = true;
				}
				
				while (true) {//BFS
					if (qi.empty() == true)break;
					if (tempzero < max)break;
					//up down left right
					//i:-1	+1	0	0
					//j:0	0	-1	1
					int ii = qi.front();	int jj = qj.front();
					qi.pop();	qj.pop();
					for (int A = 0;A < 4;A++) {
						if (ii + delti[A] >= 0 && ii + delti[A] < down && jj + deltj[A] >= 0 && jj + deltj[A] < across) {
							if (check[ii + delti[A]][jj + deltj[A]]==false) {
								if (map[ii + delti[A]][jj + deltj[A]] == 0) {
									qi.push(ii + delti[A]);
									qj.push(jj + deltj[A]);
									check[ii + delti[A]][jj + deltj[A]] = true;
									map[ii + delti[A]][jj + deltj[A]] = 2;//contaminated
									tempzero--;;

								}
							}
						}
					}	//up down left right				
				}
				//printmap(across, down, map);
				//cout << "zero:" << tempzero << "\n";
				if (tempzero > max)max = tempzero;
				map[emptySpace[a].first][emptySpace[a].second] = 0;//back to original map
				map[emptySpace[b].first][emptySpace[b].second] = 0;
				map[emptySpace[c].first][emptySpace[c].second] = 0;
			}
		}
	}
	cout << max;
}