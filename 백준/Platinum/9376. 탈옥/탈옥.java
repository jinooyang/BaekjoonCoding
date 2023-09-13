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

		public Node(int i, int j, int cnt) {
			super();
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}

	}

	static int deli[] = { 1, -1, 0, 0 };
	static int delj[] = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			char ary[][] = new char[N + 2][M + 2];
			Queue<Node> q = new ArrayDeque<>();
			int index = 0;
			Node start[] = new Node[3];

			int visited[][][] = new int[N + 2][M + 2][3];

			for (int i = 0; i < N + 2; i++) {
				for (int j = 0; j < M + 2; j++) {
					for (int k = 0; k < 3; k++) {
						visited[i][j][k] = Integer.MAX_VALUE;
					}
				}
			}
			start[index++] = new Node(0, 0, 0);

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				String s = st.nextToken();
				for (int j = 1; j <= M; j++) {
					ary[i][j] = s.charAt(j - 1);
					if (ary[i][j] == '$')
						start[index++] = new Node(i, j, 0);
				}
			}

			// 각 위치에서 BFS
			for (int r = 0; r < 3; r++) {
				Node node = start[r];
				visited[node.i][node.j][r] = 0;
				q.add(start[r]);

				while (!q.isEmpty()) {
					Node now = q.poll();
					int i = now.i;
					int j = now.j;
					int cnt = now.cnt;
					for (int idx = 0; idx < 4; idx++) {
						int di = i + deli[idx];
						int dj = j + delj[idx];
						if (di >= 0 && dj >= 0 && di < N + 2 && dj < M + 2 && ary[di][dj] != '*') {
							// 범위 안에 있고 벽이 아니라면

							int newcnt = cnt;
							if (ary[di][dj] == '#' && visited[di][dj][r] > cnt + 1) {
								newcnt++;
								visited[di][dj][r] = newcnt;
								q.add(new Node(di, dj, newcnt));
							} else if(ary[di][dj] != '#' && visited[di][dj][r] > cnt){
								visited[di][dj][r] = newcnt;
								q.add(new Node(di, dj, newcnt));
							}

						}
					}
				}

			}
			// System.out.println();
			int answer = Integer.MAX_VALUE;

			for (int i = 0; i < N + 2; i++) {
				for (int j = 0; j < M + 2; j++) {
					if (visited[i][j][0] == Integer.MAX_VALUE) {
						// System.out.print("* ");
						continue;

					}
					visited[i][j][0] += visited[i][j][1] + visited[i][j][2];
					if (ary[i][j] == '#')
						visited[i][j][0] -= 2;

					// System.out.print(visited[i][j][0] + " ");
					answer = Math.min(visited[i][j][0], answer);
				}
				// System.out.println();
			}

//			System.out.println(answer);
			sb.append(answer).append("\n");

		}
		System.out.println(sb);
	}
}