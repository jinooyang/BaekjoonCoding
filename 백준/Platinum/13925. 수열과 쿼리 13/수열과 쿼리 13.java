import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		long mul;
		long sum;

		public Node(long mul, long sum) {
			super();
			this.mul = mul;
			this.sum = sum;
		}

		public Node() {
			super();
			this.mul = 1;
			this.sum = 0;
		}

	}

	static long ary[];
	static long seg[];
	static Node lazy[];
	static final long MOD = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		ary = new long[n + 1];
		seg = new long[4 * n];
		lazy = new Node[4 * n];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			ary[i] = Long.parseLong(st.nextToken()) % MOD;
		}
		init(1, n, 1);

		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int flag = Integer.parseInt(st.nextToken());
			if (flag == 1) {
				// 구간에 더한다
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				long v = Long.parseLong(st.nextToken());
				v %= MOD;
				updateAdd(1, n, 1, x, y, v);
			}
			if (flag == 2) {
				// 구간에 곱한다
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				long v = Long.parseLong(st.nextToken());
				v %= MOD;
				updateMul(1, n, 1, x, y, v);

			}
			if (flag == 3) {
				// 구간값을 바꾼다
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				long v = Long.parseLong(st.nextToken());
				v %= MOD;
				updateMul(1, n, 1, x, y, 0);
				updateAdd(1, n, 1, x, y, v);

			}
			if (flag == 4) {
				// 구간 합을 구한다
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				long ans = getSum(1, n, 1, x, y) % MOD;
				sb.append(ans).append("\n");

			}

		}
		System.out.println(sb);
	}

	private static void updateAdd(int s, int e, int idx, int l, int r, long val) {
		updateLazy(s, e, idx);
		if (r < s || e < l)
			return;
		if (l <= s && e <= r) {
			seg[idx] += (val * (e - s + 1)) % MOD;
			seg[idx] %= MOD;
			if (s != e) {
				lazy[idx * 2].sum += val;
				lazy[idx * 2].sum %= MOD;

				lazy[idx * 2 + 1].sum += val;
				lazy[idx * 2 + 1].sum %= MOD;
			}
			return;
		}
		int mid = (s + e) >> 1;
		updateAdd(s, mid, idx * 2, l, r, val);
		updateAdd(mid + 1, e, idx * 2 + 1, l, r, val);
		seg[idx] = seg[idx * 2] % MOD + seg[idx * 2 + 1] % MOD;
		seg[idx] %= MOD;
	}

	private static void updateMul(int s, int e, int idx, int l, int r, long val) {
		updateLazy(s, e, idx);
		if (r < s || e < l)
			return;
		if (l <= s && e <= r) {
			seg[idx] = (seg[idx] * val) % MOD;
			if (s != e) {
				lazy[idx * 2].mul *= val % MOD;
				lazy[idx * 2].mul %= MOD;

				lazy[idx * 2].sum *= val % MOD;
				lazy[idx * 2].sum %= MOD;

				lazy[idx * 2 + 1].mul *= val % MOD;
				lazy[idx * 2 + 1].mul %= MOD;

				lazy[idx * 2 + 1].sum *= val % MOD;
				lazy[idx * 2 + 1].sum %= MOD;
			}
			return;
		}
		int mid = (s + e) >> 1;
		updateMul(s, mid, idx * 2, l, r, val);
		updateMul(mid + 1, e, idx * 2 + 1, l, r, val);
		seg[idx] = (seg[idx * 2] % MOD + seg[idx * 2 + 1] % MOD) % MOD;
		seg[idx] %= MOD;

	}

	private static long getSum(int s, int e, int idx, int l, int r) {
		updateLazy(s, e, idx);
		if (r < s || e < l)
			return 0;
		if (l <= s && e <= r) {
			return seg[idx] % MOD;
		}
		int mid = (s + e) >> 1;
		return (getSum(s, mid, idx * 2, l, r) % MOD + getSum(mid + 1, e, idx * 2 + 1, l, r) % MOD) % MOD;

	}

	private static void updateLazy(int s, int e, int idx) {

		if (!(lazy[idx].mul == 1 && lazy[idx].sum == 0)) {
			seg[idx] = (seg[idx] * lazy[idx].mul) % MOD;
			seg[idx] = (seg[idx] + (lazy[idx].sum * (e - s + 1)) % MOD) % MOD;
			if (s != e) {
				lazy[idx * 2].mul *= lazy[idx].mul;
				lazy[idx * 2].mul %= MOD;
				lazy[idx * 2 + 1].mul *= lazy[idx].mul;
				lazy[idx * 2 + 1].mul %= MOD;
				
				lazy[idx * 2].sum *= lazy[idx].mul;
				lazy[idx * 2].sum %= MOD;
				lazy[idx * 2 + 1].sum *= lazy[idx].mul;
				lazy[idx * 2 + 1].sum %= MOD;

				lazy[idx * 2].sum += lazy[idx].sum;
				lazy[idx * 2].sum %= MOD;
				lazy[idx * 2 + 1].sum += lazy[idx].sum;
				lazy[idx * 2 + 1].sum %= MOD;
			}
			lazy[idx].mul = 1;
			lazy[idx].sum = 0;
		}
	}

	private static long init(int s, int e, int idx) {
		lazy[idx] = new Node();
		if (s == e) {
			return seg[idx] = ary[s] % MOD;
		}
		int mid = (s + e) >> 1;
		return seg[idx] = (init(s, mid, idx * 2) % MOD + init(mid + 1, e, idx * 2 + 1) % MOD) % MOD;
	}
}