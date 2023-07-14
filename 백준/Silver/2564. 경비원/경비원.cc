#include<iostream>

using namespace std;

int main() {
	int across;	cin >> across;
	int down;	cin >> down;
	int store;	cin >> store;
	int distance[100];
	//1 북, 2 남, 3 서, 4 동
	//1,2 distance from left
	//3,4 distance from right	

	int map[100][2];

	for (int i = 0;i < store;i++) {
		int news; cin >> news;
		int d;	cin >> d;
		map[i][0] = news;
		map[i][1] = d;
	}

	int x1, x2; cin >> x1 >> x2;

	for (int i = 0;i < store;i++) {
		int y1 = map[i][0];
		int y2 = map[i][1];
		if (x1 == y1) {
			distance[i] = abs(x2 - y2);			
		}
		if (x1 == 1 && y1 == 2||x1==2&&y1==1) {
			distance[i] = min(down + x2 + y2, down + across - x2 + across - y2);
			
		}

		if (x1 == 3 && y1 == 4 || x1 == 4 && y1 == 3) {
			distance[i] = min(across + x2 + y2, across + down - x2 + down - y2);
		}


		if (x1 == 1) {
			if (y1 == 3) {
				distance[i] = x2 + y2;
			}
			if (y1 == 4) {
				distance[i] = across - x2 + y2;
			}
		}

		if (x1 == 2) {
			if (y1 == 3) {
				distance[i] = x2 + down - y2;
			}
			if (y1 == 4) {
				distance[i] = across - x2 + down - y2;
			}
		}

		if (x1 == 3 ) {
			if (y1 == 1) {
				distance[i] = x2 + y2;
			}
			if (y1 == 2) {
				distance[i] = down - x2 + y2;
			}
		}
		
		if (x1 == 4) {
			if (y1 == 1) {
				distance[i] = x2 + across - y2;
			}
			if (y1 == 2) {
				distance[i] = down - x2 + across - y2;
			}
		}
	}
	int answer = 0;
	for (int i = 0;i < store;i++) {
		answer += distance[i];
	}
	cout << answer;



}