import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
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

	static int deli[] = { 0, 0, 1, -1 };
	static int delj[] = { 1, -1, 0, 0 };
	static int N;
	static int M;
	static int ary[][];
	static int T;

	public static void main(String[] args) throws IOException {
		// 일반 bfs로 용사가 공주까지 갈수 있는 거리...칼도 벽이라 생각한다
		// 용사가 칼을 찾고 공주한테 가는 거리
		// 두 거리중 최소값
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		ary = new int[N + 1][M + 1];
		int swordi = 0;
		int swordj = 0;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 2) {
					swordi = i;
					swordj = j;
				}
				ary[i][j] = temp;
			}
		}
		// 입력 완료

		int noSword = noSwordbfs(N, M);

		int findSword = findSwordbfs(swordi, swordj);

		if (findSword != Integer.MAX_VALUE) {//칼을 찾은 경우!
			findSword = findSword + Math.abs(swordi - N) + Math.abs(swordj - M);
		}
		int best = Math.min(noSword, findSword);
		if (best > T) {
			System.out.println("Fail");
		} else
			System.out.println(best);

	}

	private static int findSwordbfs(int swordi, int swordj) {
		Queue<Node> q = new LinkedList<>();
		boolean check[][] = new boolean[N + 1][M + 1];
		q.add(new Node(1, 1, 0));
		check[1][1] = true;
		while (!q.isEmpty()) {
			Node node = q.poll();
			int i = node.i;
			int j = node.j;
			int cnt = node.cnt;

			if (i == swordi && j == swordj) {
				return cnt;
			}
			for (int idx = 0; idx < 4; idx++) {
				int di = i + deli[idx];
				int dj = j + delj[idx];
				if (di >= 1 && dj >= 1 && di <= N && dj <= M && ary[di][dj] != 1 && !check[di][dj]) {
					q.add(new Node(di, dj, cnt + 1));
					check[di][dj] = true;
				}
			}
		}
		return Integer.MAX_VALUE;// 구하지 못하는 경우
	}

	// 2를 벽이라고 생각하는 차이
	private static int noSwordbfs(int n, int m) {
		Queue<Node> q = new LinkedList<>();
		boolean check[][] = new boolean[N + 1][M + 1];
		q.add(new Node(1, 1, 0));
		check[1][1] = true;
		while (!q.isEmpty()) {
			Node node = q.poll();
			int i = node.i;
			int j = node.j;
			int cnt = node.cnt;

			if (i == n && j == m) {
				return cnt;
			}

			for (int idx = 0; idx < 4; idx++) {
				int di = i + deli[idx];
				int dj = j + delj[idx];
				if (di >= 1 && dj >= 1 && di <= N && dj <= M && ary[di][dj] != 2 && ary[di][dj] != 1
						&& !check[di][dj]) {
					q.add(new Node(di, dj, cnt + 1));
					check[di][dj] = true;
				}
			}
		}
		return Integer.MAX_VALUE;// 구하지 못하는 경우
	}
}