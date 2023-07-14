#include<iostream>
#include<string>
using namespace std;
int main() {
	//입력 받기
	int n; cin >> n;
	int sch[17][2];
	for (int i = 1; i <= n; i++) {
		cin >> sch[i][0];
		cin >> sch[i][1];
	}
	//각 요일부터 시작했을 때 얻을 수 있는 최대 이익을 생각
	//뒤에서 부터 생각
	int maxmoney[16] = { 0 };
	for (int day = n; day >= 1; day--) {
		if (day + sch[day][0] - 1 <= n) {//기간내에 할수 있는 일인가?
			maxmoney[day] = sch[day][1];		//일을 한다 가정
			maxmoney[day] += maxmoney[day + sch[day][0]];		//일 끝나고 이후 저장된 값 저장
		}
		if (maxmoney[day] < maxmoney[day + 1]) { //이 날의 일을 안하는게 더 이득일 수 도 있다
			maxmoney[day] = maxmoney[day + 1];
		}
	}
	//for (int i = 1;i <= n;i++) {
	//	cout << maxmoney[i] << " ";
	//}
	//cout << "\n";
	cout << maxmoney[1];
}