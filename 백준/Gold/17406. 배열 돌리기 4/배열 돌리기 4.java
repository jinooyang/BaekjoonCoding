import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int deli[] = { 0, 1, 0, -1 };// 우 하 좌 상
	static int delj[] = { 1, 0, -1, 0 };
	static int minSum = Integer.MAX_VALUE;
	static Deque<Integer> dq = new ArrayDeque<>();
	static int n;
	static int m;
	static int ary[][];
	static int tempary[][];
	static int k;
	static int rcs[][];

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		ary = new int[n][m];
		tempary = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
				tempary[i][j] = ary[i][j];
			}
		}

		rcs = new int[k + 1][3];

		// rcs저장하기
		for (int i = 1; i <= k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			rcs[i][0] = r;
			rcs[i][1] = c;
			rcs[i][2] = s;
			// rotate(ary, r, c, s);
		}

		comb(0, 0, new int[k]);
//		// A값 구하기
//		for (int i = 0; i < n; i++) {
//			int rowSum = 0;
//			for (int j = 0; j < m; j++) {
//				rowSum += tempary[i][j];
//			}
//			minSum = minSum <= rowSum ? minSum : rowSum;
//		}
		// 배열 출력 해보기
//		for (int ii = 0; ii < n; ii++) {
//			for (int jj = 0; jj < m; jj++) {
//				System.out.print(ary[ii][jj] + " ");
//			}
//			System.out.println();
//		}
		// 정답
		System.out.println(minSum);

	}

	private static void comb(int cnt, int beforeNum, int comblist[]) {
		if (cnt == k) {
			for (int i = 0; i < k; i++) {
				rotate(tempary, rcs[comblist[i]][0], rcs[comblist[i]][1], rcs[comblist[i]][2]);
			}
			//System.out.println();
			// A값 구하기
			for (int i = 0; i < n; i++) {
				int rowSum = 0;
				for (int j = 0; j < m; j++) {
					rowSum += tempary[i][j];
				}
				minSum = minSum <= rowSum ? minSum : rowSum;
			}

			// temp초기화
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					tempary[i][j] = ary[i][j];
				}
			}
			return;
		}
		for (int i = 1; i <= k; i++) {
			if ((beforeNum & (1 << i)) == 0) {
				comblist[cnt] = i;
				comb(cnt + 1, beforeNum | (1 << i), comblist);
			}
		}
	}

	// 시계방향으로 회전
	private static void rotate(int[][] ary, int r, int c, int s) {// 회전하자

		// layer수 만큼 반복해서 테두리를 시계 방향으로 회전한다
		for (int layer = 0; layer < s; layer++) {
			// 시작 좌표(회전할배열의 왼쪽 위)
			// 해당 테두리의 왼쪽 위 좌표
			int i = r - s - 1 + layer;
			int j = c - s - 1 + layer;

			// 덱에 테두리 값을 저장한다
			for (int idx = 0; idx < 4; idx++)
				for (int x = 0; x < 2 * (s - layer); x++) {
					dq.add(ary[i][j]);
					i += deli[idx];
					j += delj[idx];
					// i,j값은 결국 시작위치로 돌아온다
				}

			// 시계 방향으로 회전한다 -> 덱의 맨 뒤 값을 앞으로
			dq.addFirst(dq.pollLast());

			// ary에 다시 저장한다
			for (int idx = 0; idx < 4; idx++)
				for (int x = 0; x < 2 * (s - layer); x++) {
					ary[i][j] = dq.poll();
					i += deli[idx];
					j += delj[idx];
					// i,j값은 시작위치로 돌아온다
				}
		}

	}

}