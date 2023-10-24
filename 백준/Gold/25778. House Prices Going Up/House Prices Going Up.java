import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int ary[];
	static long seg[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		ary = new int[n + 1];
		seg = new long[4 * n];
		for (int i = 1; i <= n; i++) {
			ary[i] = Integer.parseInt(br.readLine());
		}
		init(1, n, 1);
		int q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			String flag = st.nextToken();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (flag.charAt(0) == 'R') {
				long x = getSum(1, n, 1, a, b);
				sb.append(x).append("\n");
			}
			if (flag.charAt(0) == 'U') {
				update(1, n, 1, a, b);
			}
		}
		System.out.println(sb);
	}

	private static long init(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = ary[s];
		int mid = (s + e) >> 1;
		return seg[idx] = init(s, mid, idx * 2) + init(mid + 1, e, idx * 2 + 1);
	}

	private static long getSum(int s, int e, int idx, int l, int r) {
		if (r < s || e < l) {
			return 0;
		}
		if (l <= s && e <= r) {
			return seg[idx];
		}
		int mid = (s + e) >> 1;
		return getSum(s, mid, idx * 2, l, r) + getSum(mid + 1, e, idx * 2 + 1, l, r);
	}

	private static void update(int s, int e, int idx, int c, int val) {
		if (c < s || e < c)
			return;
		if (s == e) {
			seg[idx] += val;
			return;
		}
		int mid = (s + e) >> 1;
		update(s, mid, idx * 2, c, val);
		update(mid + 1, e, idx * 2 + 1, c, val);
		seg[idx] = seg[idx * 2] + seg[idx * 2 + 1];
	}
}