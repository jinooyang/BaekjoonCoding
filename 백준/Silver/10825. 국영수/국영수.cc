#include<iostream>
#include<string>
#include<vector>
#include<algorithm>

using namespace std;

struct student {
	string name;
	int kor, eng, math;
};

bool cmp(student a, student b) {
	//국어도 같고 영어도 같고 수학도 같으면 이름 오름차순으로 정렬
	if (a.kor == b.kor && a.eng == b.eng && a.math == b.math) return a.name < b.name;
	//국어 같고 영어 같다면 수학 내림차순으로 정렬
	if (a.kor == b.kor && a.eng == b.eng) return a.math > b.math;
	//국어점수 같다면 영어 오름차순으로 정렬
	if (a.kor == b.kor) return a.eng < b.eng;
	//기본적으로 국어 내림차순으로 정렬
	return a.kor > b.kor;
}

int main() {
	int N;
	cin >> N;
	vector<student>v(N);
	for (int i = 0;i < N;i++) {
		cin >> v[i].name >> v[i].kor >> v[i].eng >> v[i].math;
	}
	sort(v.begin(), v.end(), cmp);

	for (int i = 0;i < N;i++) {
		cout << v[i].name << "\n";
	}
}