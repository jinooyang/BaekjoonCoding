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
		int m = Integer.parseInt(st.nextToken());
		ary = new int[n + 1];
		seg = new int[4 * n];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}
		init(1, n, 1);
		for (int i = m; i < n - m + 2; i++) {
//			System.out.print("범위");
			int l = i - m + 1;
			int r = i + m - 1;
//			System.out.println(l + " " + r);
			int x = getMax(1, n, 1, l, r);
			sb.append(x).append(" ");
		}
		System.out.println(sb);
	}

	private static int getMax(int s, int e, int idx, int l, int r) {
		if (r < s || e < l) {
			return 0;
		}
		if (l <= s && e <= r)
			return seg[idx];
		int mid = (s + e) / 2;
		int a = getMax(s, mid, idx * 2, l, r);
		int b = getMax(mid + 1, e, idx * 2 + 1, l, r);
		return Math.max(a, b);
	}

	private static int init(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = ary[s];
		int mid = (s + e) / 2;
		int a = init(s, mid, idx * 2);
		int b = init(mid + 1, e, idx * 2 + 1);

		return seg[idx] = Math.max(a, b);
	}
}