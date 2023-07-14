#include<iostream>
#include<deque>

using namespace std;

deque<int> deck[2];
deque<int> ground[2];

int check_winner() {//3번 규칙
	//do wins
	//그라운드에 나와 있는 각각의 카드 더미에서 가장 위에 위치한 카드의 숫자가 5가 나오는 순간 도도가 종을 친다.
	if (!ground[0].empty()) {
		if (ground[0].back() == 5) {
			return 0;//do wins
		}
	}
	if (!ground[1].empty()) {
		if (ground[1].back() == 5) {
			return 0;//do wins
		}
	}	
	//su wins		
	//그라운드에 나와 있는 각각의 카드 더미에서 가장 위에 위치한 카드의 숫자 합이 5가 되는 순간 수연이가 종을 친다. 단, 어느 쪽의 그라운드도 비어있으면 안된다.
	if (!ground[0].empty() && !ground[1].empty()) { 
		if(ground[0].back() + ground[1].back() == 5)
			return 1; //su wins
	}
	//no wins
	return -1;//no wins
}

void getcards(int a) {
	if (a == -1) return;//no wins
	if (a == 0) {//do gets ground cards
		for (int i = 0;i < ground[1].size();i++) {
			deck[0].push_front(ground[1][i]);//get su's cards
		}		
		for (int i = 0;i < ground[0].size();i++) {
			deck[0].push_front(ground[0][i]);//get do's cards
		}
	}
	if (a == 1) {//su gets ground cards
		for (int i = 0;i < ground[0].size();i++) {
			deck[1].push_front(ground[0][i]);//get do's cards
		}		
		for (int i = 0;i < ground[1].size();i++) {
			deck[1].push_front(ground[1][i]);//get su's cards
		}		
	}
	ground[0].clear();
	ground[1].clear();
}

int main() {
	//dodo = 0
	//su = 1
	bool turn = false;

	int cards;cin >> cards;
	int plays;cin >> plays;

	for (int i = 0;i < cards;i++) {
		int d, s;cin >> d >> s;
		deck[0].push_back(d);
		deck[1].push_back(s);
	}

	for (int p = 0;p < plays;p++) {
		int temp = deck[turn].back();			//2번 규칙
		deck[turn].pop_back();					//2번 규칙
		if (deck[turn].empty())break;			//게임 진행 도중 자신의 덱에 있는 카드의 수가 $0$개가 되는 즉시 상대방이 승리한 것으로 본다.
		ground[turn].push_back(temp);			//2번 규칙
		getcards(check_winner());
		turn = !turn;
	}
	if (deck[0].empty()) {
		cout << "su";
	}
	else if (deck[1].empty()) {
		cout << "do";
	}
	else if (deck[0].size() == deck[1].size()) {
		cout << "dosu";
	}
	else if (deck[0].size() > deck[1].size()) {
		cout << "do";
	}
	else if (deck[0].size() < deck[1].size()) {
		cout << "su";
	}



}