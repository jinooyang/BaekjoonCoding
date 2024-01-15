import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int i;
		int j;
		int idx;
		char region;

		public Node(int i, int j, int idx, char region) {
			super();
			this.i = i;
			this.j = j;
			this.idx = idx;
			this.region = region;
		}

		@Override
		public String toString() {
			return "Node [i=" + i + ", j=" + j + ", idx=" + idx + ", region=" + region + "]";
		}

	}

	static int deli[] = { 0, 0, 1, -1 };
	static int delj[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char ary[][] = new char[N][M];
//		Node start = null;
		List<Node> start = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				ary[i][j] = s.charAt(j);
				if (ary[i][j] == 'W') {
					start.add(new Node(i, j, 1, '.'));
					ary[i][j] = '.';
				}
			}
		}
		int visited[][] = new int[N][M];
		Queue<Node> q = new ArrayDeque<>();
		for (int i = 0; i < start.size(); i++) {
			q.add(start.get(i));
			visited[start.get(i).i][start.get(i).j] = 15;
		}

		while (!q.isEmpty()) {
			Node now = q.poll();
//			System.out.println(now.toString());
			// 현재노드가 초원인경우
			// 아무 방향으로 가면된다
			if (now.region == '.') {
				for (int idx = 0; idx < 4; idx++) {
					int di = deli[idx] + now.i;
					int dj = delj[idx] + now.j;
					if (ary[di][dj] != '#' && ((visited[di][dj]) & (1 << idx)) == 0) {

						q.add(new Node(di, dj, idx, ary[di][dj]));
						// 방문체크를 하는데, 초원이면 15와 OR하고 빙판이면 방향으로 OR한다
						if (ary[di][dj] == '+') {
							visited[di][dj] = visited[di][dj] | (1 << idx);
						} else {
							visited[di][dj] = 15;
						}
					}
				}
			}
			// 현재노드가 빙판인경우
			if (now.region == '+') {
				int di = deli[now.idx] + now.i;
				int dj = delj[now.idx] + now.j;
				if (ary[di][dj] == '#') {
					// 진행방향의 다음노드가 산인 경우
					// 나머지 3방향중에 간다

					for (int idx = 0; idx < 4; idx++) {
						if (now.idx == idx)
							continue;

						int di2 = deli[idx] + now.i;
						int dj2 = delj[idx] + now.j;

						if (ary[di2][dj2] != '#') {
//							System.out.println("!");
							if ((visited[di2][dj2] & (1 << idx)) == 0) {
								// 방문 가능한 노드라면

								// 빙판일 경우와 초원일경우 VISITED를 나눈다
								q.add(new Node(di2, dj2, idx, ary[di2][dj2]));
								if (ary[di2][dj2] == '.') {
									visited[di2][dj2] = 15;
								} else {
									visited[di2][dj2] = visited[di2][dj2] | (1 << idx);
								}
							}
						}
					}
				} else if (ary[di][dj] == '+') {
					// 진행 방향의 다음노드가 빙판인 경우
					// 진행방향 그대로 이어서 간다 visited OR idx
					if ((visited[di][dj] & (1 << now.idx)) == 0) {
						visited[di][dj] = visited[di][dj] | (1 << now.idx);
						q.add(new Node(di, dj, now.idx, '+'));
					}
				} else if (ary[di][dj] == '.') {
					// 진행 방향의 다음노드가 초원인 경우
					// 그대로 가는데 visited OR 15
					if (visited[di][dj] == 0) {
						visited[di][dj] = 15;
						q.add(new Node(di, dj, now.idx, '.'));
					}
				}
			}

		}
		for (int i = 0; i < start.size(); i++) {
			ary[start.get(i).i][start.get(i).j] = 'W';
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (ary[i][j] == '.' && visited[i][j] == 0) {
					sb.append('P');

				} else
					sb.append(ary[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}
}