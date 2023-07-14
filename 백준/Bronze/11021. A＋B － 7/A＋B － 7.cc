#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;


int main() {
	int a;cin >> a;
	for (int i = 0;i < a;i++) {
		int x, y;cin >> x >> y;
		cout << "Case #" << i + 1 << ": " << x + y<<endl;

	}
}