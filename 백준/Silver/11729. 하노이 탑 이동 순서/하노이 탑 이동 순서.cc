//먼저 n - 1개의 원판을 3번 장대를 거쳐 2번 장대로 옮기고,

//1번 장대에 있는 가장 큰 크기의 원판을 3번 장대로 옮긴 후,

//2번 장대에 있는 n - 1개의 원판을 1번 장대를 거쳐 3번 장대로 올려 놓는 과정이다.


#include<iostream>
using namespace std;

void hanoi(int n,int start, int to, int bypass) {
    if (n == 1) {
        cout << start << " " << to << "\n";
    }
    else {
        hanoi(n - 1, start, bypass, to);
        cout << start << " " << to << "\n";
        hanoi(n - 1, bypass, to, start);
    }
}

int main() {
    int num;
    cin >> num;
    cout << (1 << num) - 1 << "\n";
    hanoi(num, 1, 3, 2);
}