#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main() {
	int c;cin >> c;

	vector<vector<int>>points(c, vector<int>(2, 0));//0으로 초기화
	//vector push_back 보단 크기 정해주고 하는게 더 빠르다

	for (int i = 0;i < c;i++) {
		cin >> points[i][0];
		cin >> points[i][1];
	}
	/*
	vector<vector<int>>points;
	for (int i = 0;i < c;i++) {
		int x, y;cin >> x >> y;
		vector<int> xy;
		xy.push_back(x);
		xy.push_back(y);
		points.push_back(xy);
	}
	이렇게 하는거보다 vector<vector<int>>points(c, vector<int>(2, 0))으로 해주는게 더 빠른듯
	*/
	sort(points.begin(), points.end()); // cmp없어도 ㄱㅊ 이중 벡터 소팅하면 앞에꺼 순서대로 하고 같으면 뒤에꺼 알아서 해주는듯
	for (int i = 0;i < c;i++) {
		cout << points[i][0] << " " << points[i][1] << "\n";//don't use endl, consumes too much time
	}
}
