import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long seg[];
	static int ary[];
	static long sum = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		ary = new int[n];
		seg = new long[4 * n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		for (int a = 0; a < m; a++) {
			sum = 0;
			st = new StringTokenizer(br.readLine());
			int flag = Integer.parseInt(st.nextToken());
			if (flag == 1) {
				int i = Integer.parseInt(st.nextToken()) - 1;
				int j = Integer.parseInt(st.nextToken()) - 1;
				int k = Integer.parseInt(st.nextToken());
				update(0, n - 1, 1, i, j, k);
			}
			if (flag == 2) {
				int x = Integer.parseInt(st.nextToken()) - 1;
				find(0, n - 1, 1, x);
				sum += ary[x];
				sb.append(sum).append("\n");

			}
		}
		System.out.println(sb);
	}

	private static void find(int s, int e, int idx, int x) {
		if (x < s || e < x)
			return;

		if (s <= x && x <= e) {
			sum += seg[idx];
		}
		if (s == e)
			return;
		int mid = (s + e) / 2;
		find(s, mid, idx * 2, x);
		find(mid + 1, e, idx * 2 + 1, x);

	}

	private static void update(int s, int e, int idx, int l, int r, int k) {
		if (r < s || e < l)
			return;
		if (l <= s && e <= r) { // 찾는 범위면 값을 더해준다
			seg[idx] += k;
			return;
		}

		int mid = (s + e) / 2;
		update(s, mid, idx * 2, l, r, k);
		update(mid + 1, e, idx * 2 + 1, l, r, k);
	}

}