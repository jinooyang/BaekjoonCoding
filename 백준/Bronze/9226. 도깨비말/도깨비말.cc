#include<iostream>
#include<queue>
#include<string>
using namespace std;
int main()
{	string a;
	while (1)
	{
		cin >> a;
		queue<char>q;
		if (a[0] == '#')
			break;
		if (a[0] == 'a' || a[0] == 'e' || a[0] == 'i' || a[0] == 'o' || a[0] == 'u')
			cout << a << "ay" << endl;
		else
		{
			q.push(a[0]);
			for (int i = 1; i<a.size(); i++)
			{
				if (a[i] == 'a' || a[i] == 'e' || a[i] == 'i' || a[i] == 'o' || a[i] == 'u')
				{
					cout << a.substr(i, a.size() - i);
					break;
				}
				else q.push(a[i]);
			}
			while (!q.empty())
			{
				cout << q.front();
				q.pop();
			}
			cout << "ay" << endl;
		}
	}
}