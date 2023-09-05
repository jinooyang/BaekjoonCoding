import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		int ary[][] = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < n; j++) {
				ary[i][j] = s.charAt(j) - '0';
			}
		}
		int deli[] = { 0, 0, 1, -1 };
		int delj[] = { 1, -1, 0, 0 };
		int minAnswer = Integer.MAX_VALUE;
		int visited[][] = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(0, 0, 0));
		while (!q.isEmpty()) {
			Node now = q.poll();
			if (now.cnt >= minAnswer)
				continue;
			if (now.i == (n - 1) && now.j == (n - 1)) {
				if(now.cnt < minAnswer) {
					minAnswer = now.cnt;
					continue;
				}
			}
			for (int idx = 0; idx < 4; idx++) {
				int di = now.i + deli[idx];
				int dj = now.j + delj[idx];
				if (di >= 0 && dj >= 0 && di < n && dj < n) {
					if (now.cnt < visited[di][dj]) {
						visited[di][dj] = now.cnt;
						int newCnt = now.cnt;
						if (ary[di][dj] == 0)
							newCnt++;
						q.add(new Node(di, dj, newCnt));

					}
				}
			}

		}
		System.out.println(minAnswer);
	}
}