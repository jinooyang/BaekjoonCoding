#include<iostream>
using namespace std;
#include<vector>
#include<algorithm>
vector<int> all;

int getss(int a, int b, int c, int d) {
	int x[4] = { a,b,c,d };
	int min = 9999;
	for (int i = 0;i < 4;i++) {
		int a = 1000 * x[i % 4] + 100 * x[(i + 1) % 4] + 10 * x[(i + 2) % 4] + x[(i + 3) % 4];
		if (min > a)min = a;
	}
	all.push_back(min);
	return min;
}




int main() {
	int x[4];
	for (int i = 0;i < 4;i++) {
		cin >> x[i];
	}

	int min = getss(x[0], x[1], x[2], x[3]);

	for (int a = 1;a < 10;a++) { for (int b = 1;b < 10;b++) { for (int c = 1;c < 10;c++) { for (int d = 1;d < 10;d++) {getss(a, b, c, d);}}}}

	sort(all.begin(), all.end());
	all.erase(unique(all.begin(), all.end()),all.end());

	for (int i = 0;i < all.size();i++) {
		if (all[i] > min) {
			cout << i;
			break;
		}
		else if (all[i] == min) {
			cout << i + 1;
			break;
		}
		
	}
}