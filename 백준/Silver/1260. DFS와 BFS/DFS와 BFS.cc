#include<iostream>
#include<fstream>
#include<cstring>
using namespace std;

class list {

public:
	int num;
	list* next = NULL;
	//bool mark = false;
};

class stack {
public:
	stack() {
		top = NULL;
	}
	void push(int a) {
		list* temp = new list;
		temp->num = a;
		temp->next = top;
		top = temp;
	}
	int pop() {
		if (top == NULL) {
			cout << "stack is empty" << endl;
			return -1;
		}
		list* temp = top;
		int i = temp->num;
		top = top->next;
		delete(temp);
		return i;
	}
	int gettopnumber() {
		if (top == NULL) {
			cout << "stack is empty" << endl;
			return 0;
		}
		return top->num;
	}
	void printstack() {
		list* p = top;
		while (true) {
			if (p == NULL)break;
			cout << p->num;
			p = p->next;
		}
		cout << endl;
	}
	bool isEmpty() {
		if (top == NULL) {
			return true;
		}
		return false;
	}
private:
	list* top;
};

class queue {
public:
	queue() {
		head = NULL;
		tail = NULL;
	}
	void addqueue(int a) { //add to tail
		list* temp = new list;
		temp->num = a;

		if (head == NULL) {
			tail = temp;
			head = temp;
			return;
		}
		tail->next = temp;
		tail = temp;
	}
	int deletequeue() { //delete head
		if (head == NULL) {
			//cout << "empty queue" << endl;
			return -1;
		}
		if (head == tail) {
			int r = head->num;
			delete(head);
			head = tail = NULL;
			return r ;
		}
		list* temp = head;
		int r = temp->num;
		head = head->next;
		delete(temp);
		return r;
	}
	void printqueue() {
		list* p = head;
		while (p!=NULL) {
			cout << p->num;
			p = p->next;
		}
		cout << endl;
	}
	bool isEmpty(){
		if (head == tail == NULL) {
			return true;
		}
		else return false;
	}
private:
	list* head;
	list* tail;
};

class adjlist {
public:
	adjlist(int n) {
		numberofvertex = n;
		graph = new list[n];
		for (int i = 0; i < n;i++) {
			graph[i].num = i+1;
			graph[i].next = NULL;
		}
		dfsStack = new stack;
		bfsQueue = new queue;

		mark = new int[numberofvertex];
		for (int i = 0; i < numberofvertex;i++) {
			mark[i] = false;
		}

		bmark = new int[numberofvertex];
		for (int i = 0; i < numberofvertex;i++) {
			bmark[i] = false;
		}
	}
	int find_vertex(int a) {
		for (int i = 0; i < numberofvertex;i++) {
			if (graph[i].num == a) {
				//cout << "found" << endl;
				return i;
			}
		}
	}
	void addedge(int u, int v) { //그냥 뒤에 추가하지말고 크기 순서대로 추가하자
	
		int i = find_vertex(u);
		list* temp = new list;
		temp->num = v;
		list* p = &graph[i];
		list* q = p;
		while (true){
			if (p->next == NULL) {
				p->next = temp;
				break;
			}
			else {
				q = p;
				p = p->next;
				if (v <= p->num) {
					q->next = temp;
					temp->next = p;
					break;
				}
			}
		}
	}
	void print_list() {

		for (int i = 0; i < numberofvertex;i++) {
			list * p = &graph[i];
			while (true) {
				if (p == NULL)break;
				cout << p->num << "-> ";
				p = p->next;				
			}
			cout <<"NULL" << endl;
		}
	}
	void DFS(int a) {//a is starting point //자식노드중 숫자가 제일 작은놈 순서대로 봐야함
		if (mark[a - 1] == true)return;
		if (mark[a - 1] == false) {
			dfsStack->push(a);
			mark[a - 1] = true;
			int pop = dfsStack->pop();
			cout << pop << " ";
		}

		int i = find_vertex(a);
		list* p = &graph[i];				

		while (p->next != NULL) { // adj순서대로 봐서 문제가 생김 가장 작은놈순서로 봐야함
			p = p->next;
			DFS(p->num);
		}

	}
	void BFS(int a) {
		
		if (bmark[a - 1] == false) {
			bfsQueue->addqueue(a);
			bmark[a - 1] = true;
		}
		
		int delq = bfsQueue->deletequeue();
		if (delq == -1) return;
		int i = find_vertex(delq);
		list* p = &graph[i];
		while (p->next != NULL) {
			p = p->next;
			if (bmark[p->num - 1] == false) {
				bfsQueue->addqueue(p->num);
				bmark[p->num - 1] = true;
			}
		}
		cout << delq << " ";		
		
		BFS(a);
		
		
	}
private:
	int numberofvertex;
	list* graph;
	stack* dfsStack;
	queue* bfsQueue;
	int* mark;
	int* bmark;
	
};

int main() {	

	int n; // number of vertexes
	int m; // number of edges
	int start;
	
	
	//cout << "insert number of vertexes :";
	cin >> n;
	//cout << "insert number of edges : ";
	cin >> m;	
	//cout << "insert starting point : ";
	cin >> start;
	//cout << "n : " << n << ", m : " << m << ", start : " << start << endl;

	int** arr = new int* [m]; //row
	for (int i = 0; i < m;i++) {
		arr[i] = new int[2]; //col
	}
	//insert input
	for (int i = 0; i < m;i++) {
		int a, b;
		//cout << "insert edge :" << endl;
		cin >> a;
		cin >> b;
		arr[i][0] = a;
		arr[i][1] = b;
		
	}
	//print input
	/*cout << "input check : " << endl;
	for (int i = 0; i < m;i++) {
		for (int j = 0; j < 2;j++) {
			cout << arr[i][j];
		}
		cout << endl;
	}
	*/
	adjlist mainlist(n);
	for (int i = 0; i < m; i++) {
		//u arri0 v arri1
		//u arri1 v arri0
		//add to mainlist
		mainlist.addedge(arr[i][0], arr[i][1]);
		mainlist.addedge(arr[i][1], arr[i][0]);
	}
	//mainlist.print_list();

	mainlist.DFS(start);
	cout << endl;
	mainlist.BFS(start);
	//cout << endl;
//input, adjlist queue, stack success
//change input to .txt later on
//dfs bfs start


}
