import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int i;
		int j;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

	}

	static int ary[][];
	static boolean visit[][];
	static List<Node> start = new ArrayList<>();
	static int N;
	static int M;
	static int totalZero = 0;
	static int maxAnswer = 0;
	static int deli[] = { 0, 0, 1, -1 };
	static int delj[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ary = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
				if (ary[i][j] != 0)
					visit[i][j] = true;
				if (ary[i][j] == 2)
					start.add(new Node(i, j));
				if (ary[i][j] == 0)
					totalZero++;
			}
		}

		int wall[] = new int[3];
		dfs(0, -1, wall);
		System.out.println(maxAnswer);

	}

	private static void dfs(int cnt, int beforeWall, int[] wall) {
		if (cnt == 3) {
			// start를 기준으로 bfs해서 0의 개수를 카운팅한다
			Queue<Node> q = new ArrayDeque();
			Stack<Node> stack = new Stack<>();
			for (int x = 0; x < 3; x++) {
//				System.out.print("wall : " + wall[x] + " ");
				int i = wall[x] / M;
				int j = wall[x] % M;
//				System.out.print("(" + i + "," + j + ") ");
				visit[i][j] = true;
				stack.add(new Node(i, j));

			}
			for (int x = 0; x < start.size(); x++) {
				q.add(start.get(x));

			}
//			System.out.println();

			int tempMax = totalZero - 3;

			while (!q.isEmpty()) {
				Node now = q.poll();
				if (tempMax <= maxAnswer)
					break;
				for (int idx = 0; idx < 4; idx++) {
					int di = deli[idx] + now.i;
					int dj = delj[idx] + now.j;
					if (di >= 0 && dj >= 0 && di < N && dj < M && !visit[di][dj]) {
						// 범위 안에 있고 방문한적없으면
						visit[di][dj] = true;
						stack.add(new Node(di, dj));
						q.add(new Node(di, dj));
						tempMax--;
					}
				}
			}

			if (tempMax > maxAnswer)
				maxAnswer = tempMax;
			while (!stack.isEmpty()) {
				Node node = stack.pop();
				visit[node.i][node.j] = false;
			}
			return;
		}

		// 벽을 세운다
		for (int i = beforeWall + 1; i < N * M; i++) {
			if (ary[i / M][i % M] == 0) {
				wall[cnt] = i;
				dfs(cnt + 1, i, wall);
			}
		}
	}

}