#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;


int main() {
    string c;
    cin >> c;
    sort(c.begin(), c.end(), greater<char>());
    cout << c;
}