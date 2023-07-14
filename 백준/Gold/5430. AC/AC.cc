#include<iostream>
#include <string>
#include<deque>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int test; cin >> test;                             
    for (int i = 0;i < test;i++) {                    
        bool read = false;
        deque<int> d1;
        //input
        string s; cin >> s;                              
        int num; cin >> num;     
        if (num == 0) {
            char ctemp; 
            cin >> ctemp;
            cin >> ctemp;
       }
        else {
            for (int w = 0;w < num;w++) {
                char ctemp; cin >> ctemp;
                int itemp; cin >> itemp;
                d1.push_back(itemp);
            }
            char ctemp;cin >> ctemp;
        }
        //cal
        bool error = false;
        for (int k = 0;k < s.length();k++) {
            if (s[k] == 'R') {
                if (read == false) read = true;
                else read = false;
            }
            else{
                if (d1.empty())error = true;
                else {
                    if (read == false) {
                        d1.pop_front();
                    }
                    else {
                        d1.pop_back();
                    }
                }
            }
        }
        //print
        if (error == true) cout << "error\n";
        else {
            cout << "[";
            if (read == false) {              
                for (int k = 0;k < d1.size();k++) {
                    cout << d1[k];
                    if (k != d1.size() - 1)cout << ',';
                }                
            }
            else {               
                for (int k = d1.size() - 1;k >= 0;k--) {
                    cout << d1[k];
                    if (k != 0)cout << ',';
                }               
            }
            cout << "]\n";
        }

    }
}