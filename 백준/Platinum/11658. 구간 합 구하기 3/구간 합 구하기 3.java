import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;
	static int ary[][];

	static int seg[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// N*N ary
		M = Integer.parseInt(st.nextToken());
		ary = new int[N][N];
		seg = new int[4 * N][4 * N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		init_y(0, N - 1, 1);
//      printSegY();
		StringBuilder sb = new StringBuilder();
		for (int q = 0; q < M; q++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			if (w == 0) {// 구간 업데이트
				int i = Integer.parseInt(st.nextToken()) - 1;
				int j = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken());
				int diff = c - ary[i][j];
				update(0, N - 1, 1, j, i, diff);
				ary[i][j] = c;
			}
			if (w == 1) {// 구간합 구하기
				int i1 = Integer.parseInt(st.nextToken()) - 1;
				int j1 = Integer.parseInt(st.nextToken()) - 1;
				int i2 = Integer.parseInt(st.nextToken()) - 1;
				int j2 = Integer.parseInt(st.nextToken()) - 1;
				int res = getSum(0, N - 1, 1, i1, j1, i2, j2);
				sb.append(res).append("\n");
			}
		}
		System.out.println(sb);
	}

	private static int getSumX(int s, int e, int idx, int l, int r, int[] segx) {
		if (r < s || e < l) {
			return 0;
		}
		if (l <= s && e <= r) {
			return segx[idx];
		}
		int mid = (s + e) / 2;
		return getSumX(s, mid, idx * 2, l, r, segx) + getSumX(mid + 1, e, idx * 2 + 1, l, r, segx);
	}

	private static int getSum(int s, int e, int idx, int ly, int lx, int ry, int rx) {
		if (ry < s || e < ly) {
			return 0;
		}
		if (ly <= s && e <= ry) {
			return getSumX(0, N - 1, 1, lx, rx, seg[idx]);
		}
		int mid = (s + e) / 2;
		return getSum(s, mid, idx * 2, ly, lx, ry, rx) + getSum(mid + 1, e, idx * 2 + 1, ly, lx, ry, rx);
	}

	private static void updateX(int s, int e, int idx, int c, int val, int[] segx) {
		if (c < s || e < c)
			return;
		segx[idx] += val;
		if (s == e)
			return;
		int mid = (s + e) / 2;
		updateX(s, mid, idx * 2, c, val, segx);
		updateX(mid + 1, e, idx * 2 + 1, c, val, segx);
//		printSegY();
	}

	private static void update(int s, int e, int idx, int cx, int cy, int val) {
		if (cy < s || e < cy) {
			return;
		}

		updateX(0, N - 1, 1, cx, val, seg[idx]);
		if (s == e)
			return;
		int mid = (s + e) / 2;
		update(s, mid, idx * 2, cx, cy, val);
		update(mid + 1, e, idx * 2 + 1, cx, cy, val);

	}

	private static void init_y(int s, int e, int idx) {
		if (s == e) {
			// ary[s]를 가지고 세그트리를 만들자
			init_x(0, N - 1, 1, ary[s], seg[idx]);
			return;
		}
		int mid = (s + e) / 2;
		init_y(s, mid, idx * 2);
		init_y(mid + 1, e, idx * 2 + 1);
		for (int i = 0; i < 4 * N; i++) {
			seg[idx][i] = seg[idx * 2][i] + seg[idx * 2 + 1][i];
		}
	}

	private static int init_x(int s, int e, int idx, int[] aryx, int[] segx) {
		if (s == e)
			return segx[idx] = aryx[s];
		int mid = (s + e) / 2;
		return segx[idx] = init_x(s, mid, idx * 2, aryx, segx) + init_x(mid + 1, e, idx * 2 + 1, aryx, segx);
	}

	private static void printSegY() {
		System.out.println("-------------------------------------------");
		for (int i = 0; i < 4 * N; i++) {
			for (int j = 0; j < 4 * N; j++) {
				System.out.print(seg[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("-------------------------------------------");

	}

}