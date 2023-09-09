import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static long seg[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		seg = new long[4 * n];

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int flag = Integer.parseInt(st.nextToken());
			if (flag == 1) {
				// 업데이트
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken());
				update(0, n - 1, 1, a, b);
			}
			if (flag == 2) {
				// 구간합
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				sb.append(getSum(0, n - 1, 1, a, b)).append("\n");
			}
		}
		System.out.println(sb);

	}

	private static long getSum(int s, int e, int idx, int l, int r) {
		if (r < s || e < l) {
			return 0;
		}
		if (l <= s && e <= r) {
			return seg[idx];
		}
		int mid = (s + e) / 2;
		return getSum(s, mid, idx * 2, l, r) + getSum(mid + 1, e, idx * 2 + 1, l, r);
	}

	private static long update(int s, int e, int idx, int c, int val) {
		if (c < s || e < c)
			return seg[idx];

		if (s == e)
			return seg[idx] += val;

		int mid = (s + e) / 2;
		return seg[idx] = update(s, mid, idx * 2, c, val) + update(mid + 1, e, idx * 2 + 1, c, val);
	}

}