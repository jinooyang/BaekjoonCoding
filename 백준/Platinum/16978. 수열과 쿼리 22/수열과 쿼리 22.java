import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Query implements Comparable<Query> {
		int flag;
		int k;
		int i;
		int j;
		int v;
		int order;

		public Query(int flag, int k, int i, int j, int v, int order) {
			super();
			this.flag = flag;
			this.k = k;
			this.i = i;
			this.j = j;
			this.v = v;
			this.order = order;
		}

		@Override
		public int compareTo(Query o) {
			if (this.k != o.k) {
				return Integer.compare(this.k, o.k);
			} else
				return Integer.compare(this.flag, o.flag);
		}

	}

	static int queryOneCnt = 1;
	static int queryCnt = 1;
	static int queryTwoCnt = 0;
	static long ary[];
	static long seg[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		ary = new long[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}
		seg = new long[4 * n];
		init(0, n - 1, 1);

		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		List<Query> list = new ArrayList<>();
		List<Long> twoList = new ArrayList<>();
		for (int qe = 0; qe < m; qe++) {
			st = new StringTokenizer(br.readLine());
			int flag = Integer.parseInt(st.nextToken());
			if (flag == 1) {
				int i = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken());
				list.add(new Query(flag, queryOneCnt++, i, 0, v, queryCnt++));
			}
			if (flag == 2) {

				int k = Integer.parseInt(st.nextToken());
				int i = Integer.parseInt(st.nextToken()) - 1;
				int j = Integer.parseInt(st.nextToken()) - 1;
				twoList.add((long) 0);
				list.add(new Query(flag, k, i, j, 0, queryTwoCnt++));
			}
		}
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for (int qe = 0; qe < m; qe++) {
			Query now = list.get(qe);
			if (now.flag == 1) {
				update(0, n - 1, 1, now.i, now.v);
			}
			if (now.flag == 2) {
				twoList.set(now.order, getSum(0, n - 1, 1, now.i, now.j));
				// sb.append(getSum(0, n - 1, 1, now.i, now.j)).append("\n");
			}
		}
		
		for(int i=0;i<twoList.size();i++) {
			sb.append(twoList.get(i)).append("\n");
		}
		 System.out.println(sb.toString());
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
		if (c < s || e < c) {
			return seg[idx];

		}
		if (s == e) {
			return seg[idx] = val;
		}
		int mid = (s + e) / 2;
		return seg[idx] = update(s, mid, idx * 2, c, val) + update(mid + 1, e, idx * 2 + 1, c, val);
	}

	private static long init(int s, int e, int idx) {
		if (s == e) {
			return seg[idx] = ary[s];
		}
		int mid = (s + e) / 2;
		return seg[idx] = init(s, mid, idx * 2) + init(mid + 1, e, idx * 2 + 1);
	}
}