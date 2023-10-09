import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int ary[][];
	static int n;
	static int N;

	static class Node {
		int i;
		int j;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

	}

	static Stack<Node> meltStack = new Stack<>();
	static int deli[] = { 0, 0, 1, -1 };
	static int delj[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		N = 1 << n;
		ary = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
//			System.out.println((i + 1) + "번쨰 쿼리");
			int L = Integer.parseInt(st.nextToken());
			fireStorm(L);

		}
		// 얼음 A[r][c]의 합
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
//				System.out.print(ary[i][j] + " ");
				answer += ary[i][j];
			}
//			System.out.println();
		}
		System.out.println(answer);
		// 가장 큰 덩어리가 차지하는 칸의 개수.. 덩어리는 연결된 칸의 집합이다.
		Queue<Node> q = new ArrayDeque<>();
		boolean vis[][] = new boolean[N][N];
		int maxCnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (ary[i][j] != 0 && !vis[i][j]) {
					q.add(new Node(i, j));
					vis[i][j] = true;
					int cnt = 0;
					while (!q.isEmpty()) {
						cnt++;
						Node now = q.poll();
						if (cnt > maxCnt) {
							maxCnt = cnt;
						}
						for (int idx = 0; idx < 4; idx++) {
							int di = now.i + deli[idx];
							int dj = now.j + delj[idx];

							if (di >= 0 && dj >= 0 && di < N && dj < N && ary[di][dj] > 0 && !vis[di][dj]) {
								vis[di][dj] = true;
								q.add(new Node(di, dj));
							}
						}
					}
					if(cnt>maxCnt)maxCnt = cnt;
				}
			}
		}
		System.out.println(maxCnt);

	}

	private static void fireStorm(int l) {
		int L = 1 << l;

		for (int i = 0; i < N; i = i + (L)) {
			for (int j = 0; j < N; j = j + (L)) {
				rotate(i, j, L);
			}
		}

		findMelt();

		while (!meltStack.isEmpty()) {
			Node now = meltStack.pop();
			ary[now.i][now.j]--;
			if (ary[now.i][now.j] < 0)
				ary[now.i][now.j] = 0;
		}

	}

	private static void findMelt() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 사방탐색
				int iceCnt = 0;
				for (int idx = 0; idx < 4; idx++) {
					int di = i + deli[idx];
					int dj = j + delj[idx];
					if (di >= 0 && dj >= 0 && di < N && dj < N && ary[di][dj] > 0) {
						iceCnt++;
					}
				}
				if (iceCnt < 3) {
					meltStack.add(new Node(i, j));
				}
			}
		}
	}

	private static void rotate(int a, int b, int L) {
		int temp[][] = new int[L][L];
		int idx = 0;
		for (int j = b; j < b + L; j++) {
			for (int i = a + L - 1; i >= a; i--) {
				temp[idx / L][idx % L] = ary[i][j];
				idx++;
			}
		}
//		for (int i = 0; i < L; i++) {
//			for (int j = 0; j < L; j++) {
//				System.out.print(temp[i][j]);
//			}
//			System.out.println();
//		}
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < L; j++) {
				ary[i + a][j + b] = temp[i][j];
			}
		}
//		for (int i = a; i < a + L; i++) {
//			for (int j = b; j < b + L; j++) {
//				System.out.print(ary[i][j] + " ");
//			}
//			System.out.println();
//		}
	}
}