#include<stdio.h>
#include <string.h>
using namespace std;

int cnt;

int recursion(const char* s, int l, int r) {
    cnt++;
    if (l >= r) return 1;
    else if (s[l] != s[r]) return 0;
    else return recursion(s, l + 1, r - 1);
}

int isPalindrome(const char* s) {
    return recursion(s, 0, strlen(s) - 1);
}
int main() {  
    int t;
    char s[1001];
    scanf("%d", &t);

    for (int i = 0;i < t;i++) { //n testcases
        scanf("%s", s);
        printf("%d ", isPalindrome(s));
        printf("%d\n", cnt);
        cnt = 0;
    }
}