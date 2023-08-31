import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long ary[];
	static long seg[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		ary = new long[n];
		seg = new long[4 * n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}
		init(0, n - 1, 1);
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int flag = Integer.parseInt(st.nextToken());
			if (flag == 1) {
				int x = Integer.parseInt(st.nextToken()) - 1;
				int y = Integer.parseInt(st.nextToken());
				update(0, n - 1, 1, x, y);
			}
			if (flag == 2) {
				int x = Integer.parseInt(st.nextToken()) - 1;
				int y = Integer.parseInt(st.nextToken()) - 1;
				sb.append(getMin(0, n - 1, 1, x, y)).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static long getMin(int s, int e, int idx, int l, int r) {
		if (r < s || e < l) {
			return Long.MAX_VALUE;
		}
		if (l <= s && e <= r) {
			return seg[idx];
		}
		int mid = (s + e) / 2;
		return Math.min(getMin(s, mid, idx * 2, l, r), getMin(mid + 1, e, idx * 2 + 1, l, r));
	}

	private static long update(int s, int e, int idx, int c, int val) {
		if (c < s || c > e) {
			return seg[idx];
		}
		if (s == e) {
			return seg[idx] = val;
		}
		int mid = (s + e) / 2;
		return seg[idx] = Math.min(update(s, mid, idx * 2, c, val), update(mid + 1, e, idx * 2 + 1, c, val));
	}

	private static long init(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = ary[s];
		int mid = (s + e) / 2;
		return seg[idx] = Math.min(init(s, mid, idx * 2), init(mid + 1, e, idx * 2 + 1));
	}
}