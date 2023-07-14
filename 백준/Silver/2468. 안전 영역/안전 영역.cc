#include<iostream>
#include<queue>

using namespace std;
//높이는 1~100
//정사각형은 1~100
int n;
int map[100][100];
bool check[100][100] = { false };
int rain;
queue<int> qi;
queue<int> qj;
int deli[4] = { -1,1,0,0 };
int delj[4] = { 0,0,-1,1 };
//up i-1 down i+1 left j-1 right j+1	
int answer = 0;
void bfs() {
	while (1) {
		if (qi.empty())break;
		int ii, jj;
		ii = qi.front(); jj = qj.front();
		qi.pop(); qj.pop();			
		for (int d = 0;d < 4;d++) {
			int di = ii + deli[d];
			int dj = jj + delj[d];
			if (di >= 0 && di < n && dj >= 0 && dj < n) {//inside map
				if (check[di][dj] == false) {//did not check
					if (map[di][dj] >= rain) {//safe
						qi.push(di);
						qj.push(dj);
						check[di][dj] = true;
						//add child
					}
				}
			}
		}
	}
}

bool find_node() {
	for (int i = 0;i < n;i++) {
		for (int j = 0;j < n;j++) {
			if (map[i][j] >= rain && check[i][j] == false) {
				qi.push(i);
				qj.push(j);
				check[i][j] = true;
				return true;
			}
		}		
	}
	return false;
}

int main() {
	cin >> n;
	int max_hi = 0;
	for (int i = 0;i < n;i++) {//map input
		for (int j = 0;j < n;j++) {
			int temp; cin >> temp;
			if (temp > max_hi)max_hi = temp;
			map[i][j] = temp;
		}
	}
	for (rain = 0;rain <= max_hi;rain++) {
		for (int a = 0;a < n;a++) {//clear check
			for (int b = 0;b < n;b++) {
				check[a][b] = false;
			}
		}

		int safe = 0;
		while (find_node()) {
			safe++;//count safe area
			bfs();
		}
		if (safe > answer)answer = safe;
	}


	cout << answer;


}