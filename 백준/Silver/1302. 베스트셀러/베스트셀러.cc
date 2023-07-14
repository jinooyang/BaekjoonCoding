#include<iostream>
#include<string>
#include<map>
using namespace std;

int main() {
	map<string, int> m;
	int n;cin >> n;
	for (int i = 0;i < n;i++) {
		string temp; cin >> temp;
		m[temp]++;
	}
	int max=0;
	string smax="";
	for (auto iter : m) {
		if (iter.second == max) {
			if (iter.first < smax) {
				smax = iter.first;
			}
		}
		if (iter.second > max) {
			max = iter.second;
			smax = iter.first;
		}
	}
	cout << smax;
}