import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static int ary[][];
	static int N;
	static int M;
	static int K;
	static int minAnswer;
	static int deli[] = { 0, 0, 1, -1 };
	static int delj[] = { 1, -1, 0, 0 };

	static class Node {
		int i;
		int j;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + i;
			result = prime * result + j;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (i != other.i)
				return false;
			if (j != other.j)
				return false;
			return true;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());// 구슬 k개
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			ary = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					ary[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			minAnswer = Integer.MAX_VALUE;
			// 모든 경우를 다 체크한다
			int comb[] = new int[K];
			tryAll(0, comb);
			sb.append("#").append(t).append(" ").append(minAnswer).append("\n");

		}
		System.out.println(sb.toString());
	}

	private static void tryAll(int cnt, int[] comb) {
		if (cnt == K) {
//			for (int i = 0; i < K; i++) {
//				System.out.print(comb[i] + " ");
//			}
//			System.out.println();
			playGame(comb);

			return;
		}
		for (int i = 0; i < M; i++) {
			comb[cnt] = i;
			tryAll(cnt + 1, comb);
		}
	}

	private static void playGame(int[] comb) {
		int temp[][] = new int[N][M];
		// 배열 복사
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				temp[i][j] = ary[i][j];
		for (int k = 0; k < K; k++) {
			int x = comb[k];
			int y = -1;
			for (int i = 0; i < N; i++) {
				if (temp[i][x] != 0) {
					y = i;
					break;
				}
			}
			if (y == -1)
				continue;
			explodebfs(temp, x, y);
			fall(temp);
//			if (comb[0] == 2 && comb[1] == 2 && comb[2] == 6) {
//				for (int i = 0; i < K; i++) {
//					System.out.print(comb[i] + " ");
//				}
//				System.out.println();
//				for (int i = 0; i < N; i++) {
//					for (int j = 0; j < M; j++) {
//						System.out.print(temp[i][j] + " ");
//					}
//					System.out.println();
//				}
//			}
		}
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (temp[i][j] != 0)
					cnt++;
			}
		}
		if (cnt < minAnswer) {
			minAnswer = cnt;
//			for (int i = 0; i < K; i++) {
//				System.out.print(comb[i] + " ");
//			}
//			System.out.println();
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out.print(temp[i][j] + " ");
//				}
//				System.out.println();
//			}
		}

	}

	private static void fall(int[][] temp) {
		for (int j = 0; j < M; j++) {
			for (int i = N - 2; i >= 0; i--) {
				int nowi = i;
				int nowj = j;
				int now = temp[i][j];
				while (nowi < N - 1 && temp[nowi + 1][nowj] == 0) {
					nowi++;
				}
				temp[i][j] = 0;
				temp[nowi][nowj] = now;
			}
		}

	}

	private static void explodebfs(int[][] temp, int x, int y) {
		Node node = new Node(y, x);

		Queue<Node> q = new ArrayDeque<>();
		Queue<Integer> qnum = new ArrayDeque<>();
		q.add(node);
		qnum.add(temp[node.i][node.j]);
		temp[node.i][node.j] = 0;
		while (!q.isEmpty()) {
			Node now = q.poll();
			int num = qnum.poll();
			for (int mul = 1; mul < num; mul++) {
				for (int idx = 0; idx < 4; idx++) {
					int di = now.i + deli[idx] * mul;
					int dj = now.j + delj[idx] * mul;
					if (di >= 0 && dj >= 0 && di < N && dj < M && temp[di][dj] != 0) {
						if (temp[di][dj] > 1) {
							q.add(new Node(di, dj));
							qnum.add(temp[di][dj]);
						}
						temp[di][dj] = 0;
					}
				}
			}
		}
	}
}