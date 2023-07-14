#include<iostream>
#include<vector>
#include<algorithm>
#include<string>

using namespace std;

int main() {	
	vector<int> odd;
	for (int i = 0;i < 7;i++) {
		int x;cin >> x;
		if (x%2==1) odd.push_back(x);
	}

	if (odd.size() == 0) cout << -1;
	else {
		
		int sum = 0;
		
		for (int i = 0;i < odd.size();i++) {			
			sum += odd[i];
		}
		
		sort(odd.begin(), odd.end());
		cout << sum << endl;
		cout << odd[0];
	}

}
