import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int i;
		int j;
		int cnt;

		public Node(int i, int j, int cnt) {
			super();
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}

	}

	static Stack<Node> stack = new Stack<>();
	static Set<Character> set = new HashSet<>();
	static int answer = 0;
	static int n;
	static int m;
	static char ary[][];
	static int deli[] = { 0, 0, 1, -1 };
	static int delj[] = { 1, -1, 0, 0 };
	static int maxAlphabet;

	public static void main(String[] args) throws IOException {
		// DFS로 최대 경로 구하기
		// set에 지나간 알파벳 저장하기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		ary = new char[n][m];
		Set<Character> countSet = new HashSet<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < m; j++) {
				ary[i][j] = s.charAt(j);
				countSet.add(ary[i][j]);
			}
		}
		maxAlphabet = countSet.size();
		Node firstNode = new Node(0, 0, 1);
		stack.add(firstNode);
		set.add(ary[0][0]);
		DFS();
		System.out.println(answer);
	}

	private static void DFS() {

		while (!stack.isEmpty()) {
			Node now = stack.pop();
			if (answer == maxAlphabet)
				return;
			int i = now.i;
			int j = now.j;
			int cnt = now.cnt;
			// 자식을 넣자
			int child = 0;
			for (int idx = 0; idx < 4; idx++) {
				int di = i + deli[idx];
				int dj = j + delj[idx];
				if (di >= 0 && dj >= 0 && di < n && dj < m && !set.contains(ary[di][dj])) {
					set.add(ary[di][dj]);
					Node newNode = new Node(di, dj, cnt + 1);
					stack.add(newNode);
					child++;
					DFS();
					set.remove(ary[di][dj]);

				}
			}
			if (child == 0 && cnt > answer) {
				answer = cnt;
			}
		}

	}
}