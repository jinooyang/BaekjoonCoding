import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long ary[];
	static long seg[];
	static long lazy[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		ary = new long[n];
		seg = new long[4 * n];
		lazy = new long[4 * n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}
		init(0, n - 1, 1);
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			if (t == 1) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				update(0, n - 1, 1, a, b, c);
			}
			if (t == 2) {
				int a = Integer.parseInt(st.nextToken());
				sb.append(getXOR(0, n - 1, 1, a)).append("\n");
			}
		}
		System.out.println(sb);
	}

	private static void updateLazy(int s, int e, int idx) {
		if (lazy[idx] != 0) {
			seg[idx] ^= lazy[idx] * ((s - e + 1) % 2);
			if (s != e) {
				lazy[idx * 2] ^= lazy[idx];
				lazy[idx * 2 + 1] ^= lazy[idx];
			}
			lazy[idx] = 0;
		}
	}

	private static long getXOR(int s, int e, int idx, int c) {
		updateLazy(s, e, idx);
		if (c < s || e < c) {
			return 0;
		}
		if (s == e) {
			return seg[idx];
		}
		int mid = (s + e) / 2;
		return seg[idx] = getXOR(s, mid, idx * 2, c) ^ getXOR(mid + 1, e, idx * 2 + 1, c);
	}

	private static void update(int s, int e, int idx, int l, int r, int val) {

		updateLazy(s, e, idx);
		if (r < s || e < l) {
			return;
		}
		if (l <= s && e <= r) {
			seg[idx] ^= val * ((s - e + 1) % 2);
			if (s != e) {
				lazy[idx * 2] = lazy[idx * 2] ^ val;
				lazy[idx * 2 + 1] = lazy[idx * 2 + 1] ^ val;
			}
			return;
		}
		int mid = (s + e) / 2;
		update(s, mid, idx * 2, l, r, val);
		update(mid + 1, e, idx * 2 + 1, l, r, val);
		seg[idx] = seg[idx * 2] ^ seg[idx * 2 + 1];

	}

	private static long init(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = ary[s];
		int mid = (s + e) / 2;
		return init(s, mid, idx * 2) ^ init(mid + 1, e, idx * 2 + 1);
	}
}