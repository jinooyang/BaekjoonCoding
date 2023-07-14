#include<iostream>
using namespace std;
int main() {
   int N;
   cin >> N;
   if (N == 1)
      return 0;
   int i;
   for (i = 2; i <= N; i++) 
      while (N % i == 0) {
         cout << i << endl;
         N /= i;
      }
}