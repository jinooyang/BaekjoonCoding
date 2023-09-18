import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int ary[];
	static int seg[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = null;
		String s = null;
		while ((s = br.readLine()) != null) {
			StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(s);
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			ary = new int[n + 1];
			seg = new int[4 * n];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				ary[i] = Integer.parseInt(st.nextToken());
			}
			init(1, n, 1);
			for (int q = 0; q < k; q++) {
				st = new StringTokenizer(br.readLine());
				char c = st.nextToken().charAt(0);
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				if (c == 'C') {
					if (j > 0)
						j = 1;

					if (j < 0)
						j = -1;
					update(1, n, 1, i, j);

				}
				if (c == 'P') {
					int num = getMul(1, n, 1, i, j);
					if (num == 0)
						sb.append('0');
					else if (num > 0)
						sb.append('+');
					else if (num < 0)
						sb.append('-');
				}
			}
			System.out.println(sb);
		}

	}

	private static int getMul(int s, int e, int idx, int l, int r) {
		if (r < s || e < l) {
			return 1;
		}
		if (l <= s && e <= r) {
			return seg[idx];

		}
		int mid = (s + e) / 2;
		return getMul(s, mid, idx * 2, l, r) * getMul(mid + 1, e, idx * 2 + 1, l, r);
	}

	private static int update(int s, int e, int idx, int c, int val) {
		if (c < s || e < c) {
			return seg[idx];
		}

		if (s == e) {
			return seg[idx] = val;
		}

		int mid = (s + e) / 2;
		return seg[idx] = update(s, mid, idx * 2, c, val) * update(mid + 1, e, idx * 2 + 1, c, val);
	}

	private static int init(int s, int e, int idx) {
		if (s == e) {
			if (ary[s] > 0)
				return seg[idx] = 1;

			if (ary[s] == 0)
				return seg[idx] = 0;

			if (ary[s] < 0)
				return seg[idx] = -1;

		}

		int mid = (s + e) / 2;
		return seg[idx] = init(s, mid, idx * 2) * init(mid + 1, e, idx * 2 + 1);
	}
}