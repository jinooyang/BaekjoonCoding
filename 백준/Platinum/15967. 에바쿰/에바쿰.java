import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int ary[];
	static long seg[];
	static long lazy[];

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q1 = Integer.parseInt(st.nextToken());
		int q2 = Integer.parseInt(st.nextToken());
		ary = new int[n + 1];
		seg = new long[4 * n];
		lazy = new long[4 * n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			ary[i + 1] = Integer.parseInt(st.nextToken());
		}
		init(1, n, 1);
		//printseg(n);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < q1 + q2; i++) {
			st = new StringTokenizer(br.readLine());
			int flag = Integer.parseInt(st.nextToken());
			if (flag == 1) {
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				sb.append(getSum(1, n, 1, l, r)).append("\n");
			}
			if (flag == 2) {
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());
				update(1, n, 1, l, r, val);
				//printseg(n);
			}
		}
		System.out.println(sb);
	}

	private static void printseg(int n) {
		for(int ii=0;ii<4*n;ii++) {
			System.out.print(seg[ii] + " ");
		}
		System.out.println();
	}

	private static void updateLazy(int s, int e, int idx) {

		seg[idx] += lazy[idx] * (e - s + 1);
		if (s != e) {
			lazy[idx * 2] += lazy[idx];
			lazy[idx * 2 + 1] += lazy[idx];
		}
		lazy[idx] = 0;

	}

	private static void update(int s, int e, int idx, int l, int r, int val) {
		// lazy
		updateLazy(s, e, idx);
		if (r < s || e < l)
			return;
		if (l <= s && e <= r) {
			seg[idx] += val * (e - s + 1);
			if (s != e) {
				lazy[idx * 2] += val;
				lazy[idx * 2 + 1] += val;
			}
		}
		if (s == e)
			return;
		int mid = (s + e) >> 1;
		update(s, mid, idx * 2, l, r, val);
		update(mid + 1, e, idx * 2, l, r, val);
	}

	private static long getSum(int s, int e, int idx, int l, int r) {
		// lazy
		updateLazy(s, e, idx);

		if (r < s || e < l)
			return 0;
		if (l <= s && e <= r)
			return seg[idx];
		int mid = (s + e) >> 1;
		return getSum(s, mid, idx * 2, l, r) + getSum(mid + 1, e, idx * 2 + 1, l, r);
	}

	private static long init(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = ary[s];
		int mid = (s + e) >> 1;
		return seg[idx] = init(s, mid, idx * 2) + init(mid + 1, e, idx * 2 + 1);
	}

}