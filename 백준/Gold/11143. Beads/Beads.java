import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int seg[];

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < t; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());

			seg = new int[4 * N];

			for (int i = 0; i < p + q; i++) {
				st = new StringTokenizer(br.readLine());
				String s = st.nextToken();
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				if (s.charAt(0) == 'P') {
					update(1, N, 1, x, y);
				}
				if (s.charAt(0) == 'Q') {
					int sum = getSum(1, N, 1, x, y);
					sb.append(sum).append("\n");
				}
			}
		}
		System.out.println(sb);
	}

	public static int getSum(int s, int e, int idx, int l, int r) {
		if (r < s || e < l)
			return 0;
		if (l <= s && e <= r)
			return seg[idx];
		int mid = (s + e) >> 1;
		return getSum(s, mid, idx * 2, l, r) + getSum(mid + 1, e, idx * 2 + 1, l, r);
	}

	public static void update(int s, int e, int idx, int c, int val) {
		if (c < s || e < c) {
			return;
		}
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