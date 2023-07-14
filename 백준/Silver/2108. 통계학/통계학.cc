#include<iostream>
#include<algorithm>
#include<vector>
#include<map>
using namespace std;
//백준 2108번
int main() {
    int n; cin >> n;
    double sum = 0;
    vector<int> v;
    map<int, int> m;
    int maxcnt = 0;
    for (int i = 0; i < n; i++) {
        int temp; cin >> temp;
        sum += temp;
        v.push_back(temp);
        m[temp]++;
        if (m[temp] >= maxcnt) maxcnt = m[temp];
    }
    //1산술평균 
    int avg;
    if (sum >= 0)
    {
        avg = (int)(sum / n + 0.5);
    }
    else avg = (int)(sum / n - 0.5);
    cout << avg << "\n";

    //2중앙값 
    sort(v.begin(), v.end());
    cout << v[n / 2] << "\n";

    //3최빈값 

   // int maxcnt = 0;
    vector<int> vv;
    for (auto iter = m.begin(); iter != m.end(); iter++)
    {
        if (iter->second == maxcnt)vv.push_back(iter->first);
    }
    sort(vv.begin(), vv.end());
    if (vv.size() == 1)cout << vv[0] << "\n";
    else {
        cout << vv[1] << "\n";
    }
    //4범위 
    cout << v[v.size() - 1] - v[0] << "\n";

}