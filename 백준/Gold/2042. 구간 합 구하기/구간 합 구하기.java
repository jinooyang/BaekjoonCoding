import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long seg[];
	static long ary[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		ary = new long[n];
		seg = new long[4 * n + 1];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			ary[i] = Long.parseLong(st.nextToken());
		}
		init(0, n - 1, 1);
		// System.out.println(n + Arrays.toString(seg));
		for (int i = 0; i < m + k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if (a == 1) {// update
				long diff = c - ary[b - 1];
				ary[b - 1] = c;
				update(0, n - 1, 1, b - 1, diff);
				// System.out.println(Arrays.toString(seg));

			}
			if (a == 2) {// sum
				long sum = sum(0, n - 1, 1, b - 1, c - 1);
				System.out.println(sum);
			}
		}
	}

	private static long sum(int s, int e, int n, int l, long r) {
		if (r < s || e < l) {
			return 0;
		}
		if (l <= s && e <= r) {
			return seg[n];
		}
		int mid = (s + e) / 2;
		return sum(s, mid, n * 2, l, r) + sum(mid + 1, e, n * 2 + 1, l, r);
	}

	private static void update(int s, int e, int n, int c, long d) {
		if (c < s || c > e) {
			return;
		}
		seg[n] += d;
		if (s == e)
			return;
		int mid = (s + e) / 2;
		update(s, mid, n * 2, c, d);
		update(mid + 1, e, n * 2 + 1, c, d);
	}

	private static long init(int s, int e, int n) {
		if (s == e)
			return seg[n] = ary[s];
		int mid = (s + e) / 2;
		return seg[n] = init(s, mid, 2 * n) + init(mid + 1, e, 2 * n + 1);
	}
}