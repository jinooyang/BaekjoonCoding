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
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		ary = new long[N];
		seg = new long[4 * N];
		lazy = new long[4 * N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			ary[i] = Long.parseLong(st.nextToken());
		}
		init(0, N - 1, 1);
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			if (a == 1) {
				// System.out.println(1);
				long b = Long.parseLong(st.nextToken()) - 1;
				long c = Long.parseLong(st.nextToken()) - 1;
				long d = Long.parseLong(st.nextToken());
				update(0, N - 1, 1, Math.min(b, c), Math.max(b, c), d);
//				for(int ii=0;ii<4*N;ii++) {
//					System.out.print(seg[ii] + " ");
//				}
//				System.out.println();
			}
			if (a == 2) {
				// System.out.println(2);
				long b = Long.parseLong(st.nextToken()) - 1;
				long c = Long.parseLong(st.nextToken()) - 1;
				long sum = getSum(0, N - 1, 1, Math.min(b, c), Math.max(b, c));

				// System.out.println(sum);
				sb.append(sum).append("\n");
			}
		}
		System.out.println(sb);
	}

	private static void updateLazy(int s, int e, int idx) {
		if (lazy[idx] != 0) {
			seg[idx] += (e - s + 1) * lazy[idx];
			if (s != e) {
				lazy[idx * 2] += lazy[idx];
				lazy[idx * 2 + 1] += lazy[idx];
			}
			lazy[idx] = 0;
		}
	}

	private static long update(int s, int e, int idx, long l, long r, long val) {
		updateLazy(s, e, idx);
		if (r < s || e < l)
			return seg[idx];
		if (l <= s && e <= r) {// 리프 노드를 모두 업데이트한다
			seg[idx] += (e - s + 1) * val;
			if (s != e) {
				lazy[idx * 2] += val;
				lazy[idx * 2 + 1] += val;
			}
			return seg[idx];
		}
		int mid = (s + e) / 2;
		return seg[idx] = update(s, mid, idx * 2, l, r, val) + update(mid + 1, e, idx * 2 + 1, l, r, val);
	}

	private static long getSum(int s, int e, int idx, long l, long r) {
		updateLazy(s, e, idx);
		if (r < s || e < l)
			return 0;
		if (l <= s && e <= r) {
			return seg[idx];
		}
		int mid = (s + e) / 2;
		return getSum(s, mid, idx * 2, l, r) + getSum(mid + 1, e, idx * 2 + 1, l, r);
	}

	private static long init(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = ary[s];

		int mid = (s + e) / 2;
		return seg[idx] = init(s, mid, idx * 2) + init(mid + 1, e, idx * 2 + 1);
	}
}