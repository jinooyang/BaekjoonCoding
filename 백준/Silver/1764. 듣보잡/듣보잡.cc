#include<iostream>
#include<map>
#include<string>

using namespace std;

int main() {
	int n,m;
	map<string, int> mp;
	int answer = 0;

	cin >> n >> m;
	for (int i = 0;i < n;i++) {
		string tmp;
		cin >> tmp;
		mp[tmp]++;
	}
	for (int i = 0;i < m;i++) {
		string tmp;
		cin >> tmp;
		if (mp.find(tmp) != mp.end()) {
			mp[tmp]++;
			answer++;
		}
	}
	cout << answer<<"\n";
	for (auto iter = mp.begin(); iter != mp.end(); iter++)
	{
		if (iter->second == 2) {//first second is like [0], [1]
			cout << iter->first << endl;
		}
	}

	
	
}