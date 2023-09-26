import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static class Node {
		int i;
		int j;
		int cnt;
		int keySet;

		public Node(int i, int j, int cnt, int keySet) {
			super();
			this.i = i;
			this.j = j;
			this.cnt = cnt;
			this.keySet = keySet;
		}

	}

	static int deli[] = { 0, 0, 1, -1 };
	static int delj[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		char ary[][] = new char[n][m];
		boolean visit[][][] = new boolean[n][m][64];
		Node startnode = null;
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				ary[i][j] = s.charAt(j);
				if (ary[i][j] == '0') {
					startnode = new Node(i, j, 0, 0);
				}
			}
		}

		Queue<Node> q = new ArrayDeque<>();
		q.add(startnode);
		visit[startnode.i][startnode.j][0] = true;
		int answer = -1;
		while (!q.isEmpty()) {
			Node now = q.poll();

			if (ary[now.i][now.j] == '1') {
				answer = now.cnt;
				break;
			}
			for (int idx = 0; idx < 4; idx++) {
				int di = deli[idx] + now.i;
				int dj = delj[idx] + now.j;
				if (di >= 0 && dj >= 0 && di < n && dj < m && ary[di][dj] != '#') {
					// 문일경우
					int ks = now.keySet;
					char c = ary[di][dj];
					if (c >= 'A' && c <= 'F') {
						int door = doorToInt(c);
						if ((ks & door) > 0) {
							// 문을 열 수 있다.
							if (!visit[di][dj][ks]) {// 방문을 한 적이 없다.
								visit[di][dj][ks] = true;
								q.add(new Node(di, dj, now.cnt + 1, ks));
							}
						}
					} else {

						if (c >= 'a' && c <= 'f') {
							int key = keyToInt(c);
							ks = ks | key;
						}
						if (!visit[di][dj][ks]) {// 방문을 한 적이 없다.
							visit[di][dj][ks] = true;
							q.add(new Node(di, dj, now.cnt + 1, ks));
						}

					}

				}
			}
		}
		System.out.println(answer);

	}

	public static int doorToInt(char c) {
		return 1 << (c - 'A');
	}

	public static int keyToInt(char c) {
		return 1 << (c - 'a');
	}
}