import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
//	static int answerStartI = 0;
//	static int answerStartJ = 0;
//	static int answerEndI = 0;
//	static int answerEndJ = 0;
	static int maxCnt = 0;

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

	static int n;
	static int m;
	static char ary[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		ary = new char[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();

			for (int j = 0; j < m; j++) {
				ary[i][j] = s.charAt(j);
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (ary[i][j] == 'L')// 모든 L에서 출발해보자?
					bfs(i, j);
			}
		}
		System.out.println(maxCnt);

	}

	private static void bfs(int starti, int startj) {
		int deli[] = { 0, 0, 1, -1 };
		int delj[] = { 1, -1, 0, 0 };
		boolean check[][] = new boolean[n][m];
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(starti, startj, 0));
		check[starti][startj] = true;
		int maxCount = 0;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int i = node.i;
			int j = node.j;
			int cnt = node.cnt;
			if (cnt > maxCount) {
				maxCount = cnt;
			}
			for (int idx = 0; idx < 4; idx++) {
				int di = i + deli[idx];
				int dj = j + delj[idx];
				if (di >= 0 && dj >= 0 && di < n && dj < m && !check[di][dj] && ary[di][dj] == 'L') {
					q.add(new Node(di, dj, cnt + 1));
					check[di][dj] = true;
				}
			}
		}
		if (maxCnt < maxCount) {
			maxCnt = maxCount;
		}

	}
}