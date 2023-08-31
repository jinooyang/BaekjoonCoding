import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int ary[];
	static int seg[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());

		ary = new int[n + 1];
		ary[0] = Integer.MAX_VALUE;
		seg = new int[4 * n + 4];

		// ary초기화
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < n + 1; i++) {
			int x = Integer.parseInt(st.nextToken());
			ary[i] = x;
		}
		// 세그트리 초기화
		initMinTree(1, n, 1);
//		for (int i = 0; i < 4 * n; i++) {
//			System.out.print(seg[i] + " ");
//		}
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int flag = Integer.parseInt(st.nextToken());
			if (flag == 1) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				ary[x] = y;
				update(1, n, 1, x);
			}
			if (flag == 2) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				sb.append(getMax(1, n, 1, x, y)).append("\n");
			}
		}
		System.out.println(sb);

	}

	private static int getMax(int s, int e, int idx, int l, int r) {
		if (r < s || e < l) {
			return 0;
		}
		if (l <= s && e <= r) {
			return seg[idx];
		}
		int mid = (s + e) / 2;
		int left = getMax(s, mid, idx * 2, l, r);
		int right = getMax(mid + 1, e, idx * 2 + 1, l, r);
		if (ary[left] <= ary[right]) {
			return left;
		}
		return right;
	}

	private static int update(int s, int e, int idx, int chg) {
		if (chg < s || e < chg) {
			return seg[idx];
		}
		if (s == e)
			return seg[idx];
		int mid = (s + e) / 2;
		int left = update(s, mid, idx * 2, chg);
		int right = update(mid + 1, e, idx * 2 + 1, chg);
		if (ary[left] <= ary[right])
			seg[idx] = left;
		else
			seg[idx] = right;
		return seg[idx];
	}

	private static int initMinTree(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = s;
		int mid = (s + e) / 2;
		int left = initMinTree(s, mid, idx * 2);
		int right = initMinTree(mid + 1, e, idx * 2 + 1);
		if (ary[left] <= ary[right])
			seg[idx] = left;
		else
			seg[idx] = right;
		return seg[idx];
	}

}