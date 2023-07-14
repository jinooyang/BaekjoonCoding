#include<algorithm>
#include<vector>
#include<iostream>
using namespace std;


//백준 1946번
int main() {

    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int t; cin >> t;//테스트 케이스
    for (int i = 0; i < t; i++) {
        int n; cin >> n;// 지원자의 숫자
        vector <pair< int, int >> v;
        for (int j = 0; j < n; j++) {
            int a, b;//서류,면접
            cin >> a >> b;
            v.push_back(make_pair(a, b));
        }
        //서류,면접으로 정렬
        //순서대로 보기
        //확인한 면접 점수의 최대를 저장하면서 간다. 면접점수를 끌올하는사람은 합격
        sort(v.begin(), v.end());
     
        int answer = 0;
        int minnum = n+1;
        for (int q = 0; q < v.size(); q++) {
            if (v[q].second < minnum) {
                //합격
               
                answer++;
                minnum = v[q].second;
            }
        }

        cout << answer << "\n";










    }



}