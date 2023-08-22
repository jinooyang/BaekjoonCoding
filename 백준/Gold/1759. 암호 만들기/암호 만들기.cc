#include<iostream>
#include<vector>
#include<algorithm>
#include<string>
using namespace std;

vector<char> v;
int l, c;
bool chk[26];
char answer[15];

bool valid() {
	bool aeiou = false;
	int rest = 0;
	for (int i = 0;i < l;i++) {
		if (answer[i] == 'a' || answer[i] == 'e' || answer[i] == 'i' || answer[i] == 'o' || answer[i] == 'u') aeiou = true;
		else rest++;
	}
	if (aeiou == true && rest >=2)return true;
	else return false;
}

void choose(int k,int vi) {
	if (k == l) { 
		if (valid()==true) {//유효한 암호면 출력한다
			for (int b = 0;b < l;b++) {
				cout << answer[b];
			}
			cout << "\n";
		}
		return;
	}//암호보다 길면 리턴

	for (int i = vi+1;i < v.size();i++) {//케이 번째 자리의 암호 선정
		char temp = v[i];//케이 번째에 순서대로 넣기
		if (chk[temp - 'a'] == false) { //알파벳을 아직 사용 안한 상태
			answer[k] = temp;//알파벳 사용
			chk[temp - 'a'] = true;//알파벳 사용 체크
			choose(k + 1,i); //find next alphabet
			chk[temp - 'a'] = false;//알파벳 사용 체크 해제
		}
	}


}

int main() {
	
	cin >> l >> c;
	
	
	for (int i = 0;i < c;i++) {//get alphabet
		char temp; cin >> temp;
		v.push_back(temp);
	}

	sort(v.begin(), v.end());//sort alphabet


	for (int a = 0;a < 26;a++)chk[a] = false;//chk to false
	for (int a = 0;a < l;a++)answer[a] = {'0'};
	choose(0,-1);

}