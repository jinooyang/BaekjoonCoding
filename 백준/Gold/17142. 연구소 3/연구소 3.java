import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int ary[][];

	static class Node {
		int i;
		int j;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

	}

	static int N;
	static int M;
	static List<Node> virus;
	static int empty;
	static int answer;
	static Queue<Node> q;
	static Queue<Integer> qcnt;
	static int deli[] = { 0, 0, 1, -1 };
	static int delj[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		init();
		int pick[] = new int[M];
		pickVirus(0, -1, pick);
		if (answer == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(answer);

	}

	private static void pickVirus(int m, int before, int[] pick) {

		if (m == M) {
			// 해당 바이러스를 큐에 넣고 BFS한다
			int min = bfs(pick);

			answer = Math.min(min, answer);
			return;
		}
		for (int i = before + 1; i < virus.size(); i++) {
			pick[m] = i;
			pickVirus(m + 1, i, pick);
		}
	}

	private static int bfs(int[] pick) {

		boolean visited[][] = new boolean[N][N];

		for (int i = 0; i < M; i++) {
			Node v = virus.get(pick[i]);
			q.add(v);
			qcnt.add(0);
			visited[v.i][v.j] = true;
		}
		int change = 0;
		int maxCnt = 0;
		while (!q.isEmpty()) {
			Node now = q.poll();
			int cnt = qcnt.poll();
			// 최대 시간을 기록할 수 있다
			if (ary[now.i][now.j] != 2) {
				maxCnt = Math.max(cnt, maxCnt);
			}

			if (ary[now.i][now.j] == 0) {
				// 빈칸을 채웠으면 기록한다
				change++;
			}
			for (int idx = 0; idx < 4; idx++) {
				int di = deli[idx] + now.i;
				int dj = delj[idx] + now.j;
				if (di >= 0 && dj >= 0 && di < N && dj < N && ary[di][dj] != 1 && !visited[di][dj]) {
					// 벽이 아니고 범위 내에 있다면
					q.add(new Node(di, dj));
					qcnt.add(cnt + 1);
					visited[di][dj] = true;
				}
			}
		}
		if (change == empty)// 빈칸이 모두 채워졌다면
			return maxCnt;
		else
			return Integer.MAX_VALUE;
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		answer = Integer.MAX_VALUE;
		empty = 0;
		q = new ArrayDeque<>();
		qcnt = new ArrayDeque<>();
		virus = new ArrayList<>();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ary = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
				if (ary[i][j] == 2)
					virus.add(new Node(i, j));
				if (ary[i][j] == 0)
					empty++;
			}
		}
	}
}