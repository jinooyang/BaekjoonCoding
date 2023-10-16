import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int ary[];
	static int maxSeg[];
	static int minSeg[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		ary = new int[N + 1];
		maxSeg = new int[4 * N];
		minSeg = new int[4 * N];

		for (int i = 1; i <= N; i++) {
			ary[i] = Integer.parseInt(br.readLine());
		}

		initMax(1, N, 1);
		initMin(1, N, 1);
		StringBuilder sb = new StringBuilder();
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			sb.append(getMax(1, N, 1, l, r) - getMin(1, N, 1, l, r)).append("\n");
		}
		System.out.println(sb);

	}

	private static int initMax(int s, int e, int idx) {
		if (s == e)
			return maxSeg[idx] = ary[s];
		int mid = (s + e) >> 1;
		return maxSeg[idx] = Math.max(initMax(s, mid, idx * 2), initMax(mid + 1, e, idx * 2 + 1));
	}

	private static int initMin(int s, int e, int idx) {
		if (s == e)
			return minSeg[idx] = ary[s];
		int mid = (s + e) >> 1;
		return minSeg[idx] = Math.min(initMin(s, mid, idx * 2), initMin(mid + 1, e, idx * 2 + 1));
	}

	private static int getMax(int s, int e, int idx, int l, int r) {
		if (r < s || e < l)
			return 0;
		if (l <= s && e <= r)
			return maxSeg[idx];
		int mid = (s + e) >> 1;
		return Math.max(getMax(s, mid, idx * 2, l, r), getMax(mid + 1, e, idx * 2 + 1, l, r));
	}

	private static int getMin(int s, int e, int idx, int l, int r) {
		if (r < s || e < l)
			return Integer.MAX_VALUE;
		if (l <= s && e <= r)
			return minSeg[idx];
		int mid = (s + e) >> 1;
		return Math.min(getMin(s, mid, idx * 2, l, r), getMin(mid + 1, e, idx * 2 + 1, l, r));
	}
}