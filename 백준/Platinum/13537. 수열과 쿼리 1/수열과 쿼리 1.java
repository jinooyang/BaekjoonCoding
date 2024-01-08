import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static List<Integer> seg[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		seg = new List[4 * n];
		for (int i = 0; i < 4 * n; i++) {
			seg[i] = new ArrayList<>();
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int x = Integer.parseInt(st.nextToken());
			update(0, n - 1, 1, i, x);
		}
		for (int i = 0; i < 4 * n; i++) {
			Collections.sort(seg[i]);
		}
		StringBuilder sb = new StringBuilder();
		int Q = Integer.parseInt(br.readLine());
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken()) - 1;
			int r = Integer.parseInt(st.nextToken()) - 1;
			int k = Integer.parseInt(st.nextToken());

			sb.append(query(0, n - 1, 1, l, r, k)).append("\n");
		}
		System.out.println(sb);
		br.close();
	}

	private static int query(int s, int e, int idx, int l, int r, int k) {
		if (r < s || e < l)
			return 0;
		if (l <= s && e <= r) {
			int index = binarySearch(seg[idx], k);
			return seg[idx].size() - index;
		}

		int mid = (s + e) >> 1;
		int a = query(s, mid, idx * 2, l, r, k);
		int b = query(mid + 1, e, idx * 2 + 1, l, r, k);

		return a + b;
	}

	private static void update(int s, int e, int idx, int c, int val) {
		if (c < s || e < c)
			return;

		seg[idx].add(val);
		if (s == e)
			return;
		int mid = (s + e) >> 1;
		update(s, mid, idx * 2, c, val);
		update(mid + 1, e, idx * 2 + 1, c, val);
	}

	private static int binarySearch(List<Integer> list, int k) {
		int l = 0;
		int r = list.size();

		while (l < r) {
			int mid = (l + r) >> 1;
			if (list.get(mid) <= k) {
				l = mid + 1;
			} else {
				r = mid;
			}
		}
		return r;

	}
}