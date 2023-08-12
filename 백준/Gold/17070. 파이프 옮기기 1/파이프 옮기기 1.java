import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static class Node {
		int i;
		int j;
		int before;

		public Node(int i, int j, int before) {
			super();
			this.i = i;
			this.j = j;
			this.before = before;
		}

	}

	static int n;
	static int ary[][];
	static int deli[] = { 0, 1, 1 };
	static int delj[] = { 1, 1, 0 };
	static Stack<Node> stack = new Stack<>();
	static int answer = 0;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		ary = new int[n][n];
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
				if (ary[i][j] == 1)
					visited[i][j] = true;
			}
		}
		stack.add(new Node(0, 1, 0));
		visited[0][1] = true;
		if (ary[n - 1][n - 1] != 1)
			DFS();
		System.out.println(answer);
	}

	private static void DFS() {
		if (stack.isEmpty())
			return;
		Node node = stack.pop();
		int i = node.i;
		int j = node.j;
		int before = node.before;
		if (i == n - 1 && j == n - 1) {
			answer++;
			return;
		}
		for (int idx = 0; idx < 3; idx++) {

			int di = i + deli[idx];
			int dj = j + delj[idx];
			if (di >= 0 && di < n && dj >= 0 && dj < n && visited[di][dj] == false && checkDirection(idx, before)) {
				if (idx == 1) {
					if (!visited[di - 1][dj] && !visited[di][dj - 1]) {

						stack.add(new Node(di, dj, idx));
						visited[di][dj] = true;
						visited[di - 1][dj] = true;
						visited[di][dj - 1] = true;
						DFS();
						visited[di][dj] = false;
						visited[di - 1][dj] = false;
						visited[di][dj - 1] = false;

					}
				} else {
					stack.add(new Node(di, dj, idx));
					visited[di][dj] = true;
					DFS();
					visited[di][dj] = false;
				}
			}
		}
	}

	private static boolean checkDirection(int idx, int before) {
		if (idx == before)
			return true;
		if (idx == 0 && before == 1) {
			return true;
		}
		if (idx == 1) {
			return true;
		}
		if (idx == 2 && before == 1) {
			return true;
		}
		return false;
	}
}