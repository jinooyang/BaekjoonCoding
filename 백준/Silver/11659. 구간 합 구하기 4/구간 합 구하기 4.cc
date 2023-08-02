#include<iostream>
#include<vector>
using namespace std;

int main() {
	
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int n;cin >> n;
	int m;cin >> m;
	vector<int> v;
	v.push_back(0);
	int sum = 0;
	for (int i = 0;i < n;i++) {
		int temp;cin >> temp;
		sum += temp;
		v.push_back(sum);
	}
	for (int i = 0;i < m;i++) {
		int start, end;cin >> start >> end;
		int sum = 0;
		cout<<v[end]- v[start-1]<<"\n";
		
	}

}