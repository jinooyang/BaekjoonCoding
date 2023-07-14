#include<iostream>
using namespace std;
#include<queue>

int gr[100001];
queue<int> q;
queue<int> qcnt;

void bfs() {
    //자식 노드가 곱2 혹은 +-1 인 bfs를 구현해보자
    if (q.empty() == false) {
        int k = q.front(); int c = qcnt.front();
        q.pop(); qcnt.pop();
        //cout << "pop" << k <<"grk : " <<gr[k]<< endl;
        if (gr[k] == 2) { cout << c; return; }

        if (k + 1 <= 100000) {
            if (gr[k + 1] != 1) {
                if (gr[k + 1] != 2)gr[k + 1] = 1;
                q.push(k + 1); qcnt.push(c + 1);
                //cout << "push" << k + 1 << endl;
            }
        }
        if (k - 1 >= 0) {
            if (gr[k - 1] != 1) {
                if (gr[k - 1] != 2)gr[k - 1] = 1;
                q.push(k - 1); qcnt.push(c + 1);
                // cout << "push" << k - 1 << endl;
            }
        }
        if (k * 2 <= 100000) {
            if (gr[k * 2] != 1) {
                if (gr[k * 2] != 2)gr[k * 2] = 1;
                q.push(k * 2); qcnt.push(c + 1);
                //cout << "push" << k * 2 << endl;
            }
        }

        bfs();
    }
}

int main() {
    int n, k;
    cin >> n >> k;
    for (int i = 0; i <= 100000;i++) {
        gr[i] = 0;
    }
    if (n == k) { cout << 0; return 0; }
    gr[k] = 2;
    gr[n] = 1;
    q.push(n); qcnt.push(0);
    bfs();

}