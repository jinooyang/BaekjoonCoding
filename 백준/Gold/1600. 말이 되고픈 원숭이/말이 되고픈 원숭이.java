import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int i;
		int j;
		int cnt;
		int kcnt;

		public Node(int i, int j, int cnt, int kcnt) {
			super();
			this.i = i;
			this.j = j;
			this.cnt = cnt;
			this.kcnt = kcnt;
		}

		@Override
		public String toString() {
			return "Node [i=" + i + ", j=" + j + ", cnt=" + cnt + ", kcnt=" + kcnt + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int ary[][] = new int[n][m];
		int visited[][] = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(0, 0, 0, 0));
		visited[0][0] = 1;
		int answer = -1;
		int deli[] = { 0, 0, 1, -1, -2, -2, -1, -1, 1, 1, 2, 2 };
		int delj[] = { 1, -1, 0, 0, -1, 1, -2, 2, -2, 2, -1, 1 };
		while (!q.isEmpty()) {
			Node node = q.poll();
			int i = node.i;
			int j = node.j;
			int cnt = node.cnt;
			int kcnt = node.kcnt;
			// System.out.println(node);
			if (i == n - 1 && j == m - 1) {
				answer = cnt;
				break;
			}
			int num = 12;
			if (kcnt == k)
				num = 4;
			for (int idx = 0; idx < num; idx++) {
				int di = i + deli[idx];
				int dj = j + delj[idx];
				if (di >= 0 && dj >= 0 && di < n && dj < m && ary[di][dj] == 0) {// 범위 안에 있다면
					int v = visited[di][dj];
					int newK = kcnt;
					if (idx >= 4)
						newK++;
					if ((v & (1 << newK)) == 0) {

						q.add(new Node(di, dj, cnt + 1, newK));
						visited[di][dj] = (((1 << (k + 1)) - 1) & (1 << newK)) | v;
					}
				}
			}

		}
		System.out.println(answer);
	}
}