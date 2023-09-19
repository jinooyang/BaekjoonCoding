import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer> seg[];
	static int segCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		seg = new List[4 * n];
		init(1, n, 1);
//		for (int i = 0; i < 4 * n; i++) {
//			seg[i] = new ArrayList<>();
//		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			int x = Integer.parseInt(st.nextToken());
			update(1, n, 1, i, x);
		}

		sortSeg(1, n, 1);

//		for (int i = 0; i < 4 * n; i++) {
//			Collections.sort(seg[i]);
//		}
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int lastAnswer = 0;
		for (int q = 0; q < m; q++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken()) ^ lastAnswer;
			int j = Integer.parseInt(st.nextToken()) ^ lastAnswer;
			int k = Integer.parseInt(st.nextToken()) ^ lastAnswer;
			int x = query(1, n, 1, i, j, k);
			lastAnswer = x;
			sb.append(x).append("\n");
		}
		System.out.println(sb);
		br.close();
	}

	public static int query(int s, int e, int idx, int l, int r, int k) {
		if (r < s || e < l)
			return 0;
		if (l <= s && e <= r) {
			int x = upperBound(seg[idx], k);
			int size = seg[idx].size();
			return size - x;
		}

		int mid = (s + e) >> 1;
		return query(s, mid, idx * 2, l, r, k) + query(mid + 1, e, idx * 2 + 1, l, r, k);
	}

	public static void update(int s, int e, int idx, int c, int val) {
		if (c < s || e < c)
			return;

		seg[idx].add(val);
		if (s == e)
			return;
		int mid = (s + e) >> 1;

		update(s, mid, idx * 2, c, val);
		update(mid + 1, e, idx * 2 + 1, c, val);
	}

	public static void init(int s, int e, int idx) {
		if (s == e) {
			seg[idx] = new ArrayList<>();
			return;
		}

		seg[idx] = new ArrayList<>();
		int mid = (s + e) >> 1;
		init(s, mid, idx * 2);
		init(mid + 1, e, idx * 2 + 1);
	}

	private static void sortSeg(int s, int e, int idx) {
		Collections.sort(seg[idx]);
		if (s == e)
			return;
		int mid = (s + e) >> 1;

		sortSeg(s, mid, idx * 2);
		sortSeg(mid + 1, e, idx * 2 + 1);

	}

	public static int upperBound(List<Integer> list, int k) {
		int l = 0;
		int r = list.size();
		while (l < r) {
			int mid = (l + r) >> 1;
			if (list.get(mid) <= k)
				l = mid + 1;// 오른쪽에서 찾기
			else
				r = mid;// 왼쪽에서 찾기

		}

		return r;
	}
}