import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int i;
		int j;
		boolean wall;
		int cnt;

		public Node(int i, int j, boolean wall, int cnt) {
			super();
			this.i = i;
			this.j = j;
			this.wall = wall;
			this.cnt = cnt;
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int ary[][] = new int[n][m];
		boolean withOutWallCheck[][] = new boolean[n][m];
		boolean withWallCheck[][] = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < m; j++) {
				ary[i][j] = s.charAt(j) - '0';
			}
		}
		int deli[] = { 0, 0, 1, -1 };
		int delj[] = { 1, -1, 0, 0 };
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, false, 1));
		withWallCheck[0][0] = true;
		withOutWallCheck[0][0] = true;// 부수고 나서 안부수고 달려온 경로를 다시 가면 안된다
		int answer = -1;
		while (!q.isEmpty()) {
			Node node = q.poll();
			int i = node.i;
			int j = node.j;
			int cnt = node.cnt;
			boolean wall = node.wall;
			if (i == (n - 1) && j == (m - 1)) {
				answer = cnt;
				break;
			}

			for (int x = 0; x < 4; x++) {
				int di = i + deli[x];
				int dj = j + delj[x];
				if (di >= 0 && dj >= 0 && di < n && dj < m) {
					// 벽을 부수지 않고 여기 까지 왔을 때 -> withoutwall
					if (!wall) {
						if (!withOutWallCheck[di][dj]) {
							if (ary[di][dj] == 0) {
								//
								q.add(new Node(di, dj, wall, cnt + 1));
								withOutWallCheck[di][dj] = true;
							}
							if (ary[di][dj] == 1 && !withWallCheck[di][dj]) {
								//
								q.add(new Node(di, dj, !wall, cnt + 1));
								withWallCheck[di][dj] = true;
							}
						}
					}
					// 벽을 부수고 여기까지 왔을 때 -> withwall
					else {
						if (ary[di][dj] == 0 && !withWallCheck[di][dj] && !withOutWallCheck[di][dj]) {
							//
							q.add(new Node(di, dj, wall, cnt + 1));
							withWallCheck[di][dj] = true;
						}
					}
				}
			}
		}
		System.out.println(answer);

	}
}