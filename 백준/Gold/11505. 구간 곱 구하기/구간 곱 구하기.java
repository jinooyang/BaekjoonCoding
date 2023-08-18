import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int arr[];
	static long tree[];
	static final long mod = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		arr = new int[N + 1];
		tree = new long[4 * N];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}

		init(1, N, 1);

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());

			if (a == 1) {
				int idx = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int from = arr[idx];
				update(1, N, 1, idx, from, to);
				arr[idx] = to;

			}
			if (a == 2) {
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());

				long mul = multiply(1, N, left, right, 1);
				sb.append(mul).append("\n");
			}

		}
		System.out.println(sb);
	}

	public static long multiply(int start, int end, int left, int right, int node) {
		if (left > end || right < start)
			return 1;
		if (left <= start && end <= right)
			return tree[node];
		int mid = (start + end) / 2;
		return (multiply(start, mid, left, right, node * 2) * multiply(mid + 1, end, left, right, node * 2 + 1)) % mod;
	}

	private static long update(int s, int e, int n, int idx, long orgval, long newval) {
		if (idx < s || e < idx) {
			return tree[n];
		}
		if (s == e) {
			tree[n] = (orgval == 0) ? newval : (tree[n] / orgval * newval) % mod;
			return tree[n];
			// return seg[n] = newval;
		}
		int mid = (s + e) / 2;
		return tree[n] = (update(s, mid, n * 2, idx, orgval, newval)
				* update(mid + 1, e, n * 2 + 1, idx, orgval, newval)) % mod;

	}

	private static long init(int s, int e, int n) {
		if (s == e)
			return tree[n] = arr[s];
		int mid = (s + e) / 2;
		return tree[n] = (init(s, mid, n * 2) * init(mid + 1, e, n * 2 + 1)) % mod;
	}

}