import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int seg[];
	static int lazy[];

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		seg = new int[4 * n];
		lazy = new int[4 * n];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int o = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			if (o == 0) {
				update(1, n, 1, s, t);
			}
			if (o == 1) {
				int res = getSum(1, n, 1, s, t);
				sb.append(res).append("\n");
			}
		}
		System.out.println(sb);

	}

	static private void updateLazy(int s, int e, int idx) {
		if (lazy[idx] % 2 != 0) {
			if (s != e) {
				lazy[idx * 2] += lazy[idx];
				lazy[idx * 2 + 1] += lazy[idx];
			}
			seg[idx] = e - s + 1 - seg[idx];
			lazy[idx] = 0;
		}

	}

	static private int getSum(int s, int e, int idx, int l, int r) {
		updateLazy(s, e, idx);
		if (r < s || e < l)
			return 0;
		if (l <= s && e <= r)
			return seg[idx];

		int mid = (s + e) >> 1;

		return getSum(s, mid, idx * 2, l, r) + getSum(mid + 1, e, idx * 2 + 1, l, r);
	}

	static private void update(int s, int e, int idx, int l, int r) {
		updateLazy(s, e, idx);
		if (r < s || e < l)
			return;
		if (l <= s && e <= r) {
			int size = e - s + 1;
			seg[idx] = size - seg[idx];
			if (s != e) {
				lazy[idx * 2]++;
				lazy[idx * 2 + 1]++;
			}
			return;
		}
		int mid = (s + e) >> 1;
		update(s, mid, idx * 2, l, r);
		update(mid + 1, e, idx * 2 + 1, l, r);
		seg[idx] = seg[idx * 2] + seg[idx * 2 + 1];

	}
}