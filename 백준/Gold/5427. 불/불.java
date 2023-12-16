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
		boolean isFire;
		int cnt;

		public Node(int i, int j, boolean isFire, int cnt) {
			super();
			this.i = i;
			this.j = j;
			this.isFire = isFire;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Node [i=" + i + ", j=" + j + ", isFire=" + isFire + ", cnt=" + cnt + "]";
		}

	}

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());

			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());

			char ary[][] = new char[N][M];
			Node start = null;
			List<Node> fire = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < M; j++) {
					ary[i][j] = s.charAt(j);

					if (ary[i][j] == '@')
						start = new Node(i, j, false, 0);
					if (ary[i][j] == '*')
						fire.add(new Node(i, j, true, 0));
				}
			}

			Queue<Node> q = new ArrayDeque<>();
			for (int i = 0; i < fire.size(); i++) {
				q.add(fire.get(i));
			}
			q.add(start);
			int answer = -1;

			int deli[] = { 1, -1, 0, 0 };
			int delj[] = { 0, 0, -1, 1 };

			while (!q.isEmpty()) {
				Node now = q.poll();
//				System.out.println(now);
				if (!now.isFire && (now.i == 0 || now.i == N - 1 || now.j == 0 || now.j == M - 1)) {
					answer = now.cnt + 1;
					break;
				}
//				boolean possible = false;
				for (int idx = 0; idx < 4; idx++) {
					int di = now.i + deli[idx];
					int dj = now.j + delj[idx];

					if (di >= 0 && dj >= 0 && di < N && dj < M) {// 범위 안에 있고
						if (now.isFire) {// 불의 경우
							if (ary[di][dj] != '#' && ary[di][dj] != '*') {// 벽이 아니면
								ary[di][dj] = '*';// 불로 바꾼다
								q.add(new Node(di, dj, now.isFire, now.cnt + 1));
							}
						}
						if (!now.isFire) {// 불이 아닌 경우
							if (ary[di][dj] == '.') {
								ary[di][dj] = '@';// 골뱅이로 방문 표시해준다
								q.add(new Node(di, dj, now.isFire, now.cnt + 1));
//								possible = true;// 갈 길이 있다고 표시한다
							}

						}
					}

				}
//				if (!now.isFire && !possible) {
//					break;// 정답을 찾기 전에 이미 죽음
//				}
			}
			if (answer == -1) {
				System.out.println("IMPOSSIBLE");
			} else {
				System.out.println(answer);
			}

		}
	}
}