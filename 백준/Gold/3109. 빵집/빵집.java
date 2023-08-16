import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int deli[] = { -1, 0, 1 };
	static int delj[] = { 1, 1, 1 };
	static Stack<Node> stack = new Stack<>();
	static int c;
	static int r;
	static boolean found;
	static int ary[][];
	static int answer = 0;

	static class Node {
		int i;
		int j;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		ary = new int[r][c];
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < c; j++) {
				char temp = s.charAt(j);
				if (temp == 'x')
					ary[i][j] = 8;
			}
		}

		for (int i = 0; i < r; i++) {
			stack.clear();
			found = false;
			stack.add(new Node(i, 0));
			dfs(i, 0);
			if (found)
				answer++;
		}
		System.out.println(answer);

		// printAry();

	}

	private static void printAry() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(ary[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void dfs(int i, int j) {
		if (found)
			return;
		if (stack.isEmpty())
			return;
		Node now = stack.pop();
		if (now.j == c - 1) {
			found = true;
			stack.clear();
		}

		for (int idx = 0; idx < 3; idx++) {
			int di = now.i + deli[idx];
			int dj = now.j + delj[idx];
			if (di >= 0 && dj >= 0 && di < r && dj < c && ary[di][dj] == 0) {
				stack.add(new Node(di, dj));
				ary[di][dj] = 1;
				dfs(di, dj);
				if (found)
					return;
			 //	ary[di][dj] = 0;

			}
		}
	}
}