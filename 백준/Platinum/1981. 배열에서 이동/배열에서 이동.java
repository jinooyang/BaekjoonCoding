import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int ary[][];
	static int max = 0;
	static int min = 200;

	static class Node {
		int i;
		int j;
		int max;
		int min;

		public Node(int i, int j, int max, int min) {
			super();
			this.i = i;
			this.j = j;
			this.max = max;
			this.min = min;
		}

	}

	static int deli[] = { 0, 0, 1, -1 };
	static int delj[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		ary = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(ary[i][j], max);
				min = Math.min(ary[i][j], min);
			}
		}
		int answer = Integer.MAX_VALUE;
		int big = Math.max(ary[0][0], ary[N - 1][N - 1]);
		int small = Math.min(ary[0][0], ary[N - 1][N - 1]);
		while (small >= min) {

			int s = big;
			int e = max;

			while (s <= e) {
				int mid = (s + e) / 2;
				int x = bfs(small, mid);
				if (x != -1) {
					answer = Math.min(x, answer);
					e = mid - 1;
					continue;
				} else {
					s = mid + 1;
				}
			}

			small--;
		}
		System.out.println(answer);
	}

	private static int bfs(int l, int r) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(0, 0, ary[0][0], ary[0][0]));
		boolean visited[][] = new boolean[N][N];
		int result = -1;
		while (!q.isEmpty()) {
			Node now = q.poll();
			if (now.i == N - 1 && now.j == N - 1) {
				result = now.max - now.min;
				break;
			}
			for (int idx = 0; idx < 4; idx++) {
				int di = now.i + deli[idx];
				int dj = now.j + delj[idx];
				if (di >= 0 && dj >= 0 && di < N && dj < N && !visited[di][dj] && ary[di][dj] >= l
						&& ary[di][dj] <= r) {
					visited[di][dj] = true;
					q.add(new Node(di, dj, Math.max(now.max, ary[di][dj]), Math.min(now.min, ary[di][dj])));
				}
			}

		}

		return result;
	}
}