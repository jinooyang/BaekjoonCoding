import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int ary[];
	static int maxtree[];
	static int mintree[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		ary = new int[n];
		maxtree = new int[4 * n];
		mintree = new int[4 * n];
		// ary초기화
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			ary[i] = x;
		}
		// 세그트리 초기화
		initMaxTree(0, n - 1, 1);
		initMinTree(0, n - 1, 1);

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken()) - 1;
			int r = Integer.parseInt(st.nextToken()) - 1;
			System.out.println(getMin(0, n - 1, 1, l, r) + " " + getMax(0, n - 1, 1, l, r));

		}

	}

	private static int getMax(int s, int e, int n, int left, int right) {
		if (left > e || s > right) {
			return 0;
		}
		if (left <= s && e <= right) {
			return maxtree[n];
		}
		int mid = (s + e) / 2;
		return Math.max(getMax(s, mid, n * 2, left, right), getMax(mid + 1, e, n * 2 + 1, left, right));
	}

	private static int getMin(int s, int e, int n, int left, int right) {
		if (left > e || s > right) {
			return Integer.MAX_VALUE;
		}
		if (left <= s && e <= right) {
			return mintree[n];
		}
		int mid = (s + e) / 2;
		return Math.min(getMin(s, mid, n * 2, left, right), getMin(mid + 1, e, n * 2 + 1, left, right));
	}

	private static int initMaxTree(int s, int e, int n) {
		if (s == e)
			return maxtree[n] = ary[s];
		int mid = (s + e) / 2;
		return maxtree[n] = Math.max(initMaxTree(s, mid, n * 2), initMaxTree(mid + 1, e, n * 2 + 1));
	}

	private static int initMinTree(int s, int e, int n) {
		if (s == e)
			return mintree[n] = ary[s];
		int mid = (s + e) / 2;
		return mintree[n] = Math.min(initMinTree(s, mid, n * 2), initMinTree(mid + 1, e, n * 2 + 1));
	}
}