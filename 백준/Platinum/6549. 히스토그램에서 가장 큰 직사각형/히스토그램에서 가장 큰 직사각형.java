import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static long ary[];
	static int seg[];

	static class Node {
		long minHeight;
		long maxArea;

		public Node(long minHeight, long maxArea) {
			super();
			this.minHeight = minHeight;
			this.maxArea = maxArea;
		}

	}

	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		while (true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			if (n == 0)
				break;
			ary = new long[n + 1];
			ary[n] = Long.MAX_VALUE;
			seg = new int[4 * n];
			for (int i = 0; i < n; i++) {
				ary[i] = Integer.parseInt(st.nextToken());
			}
			init(0, n - 1, 1);
			sb.append(findMax(0, n - 1)).append("\n");
		}
		System.out.println(sb);
	}

	private static long findMax(int s, int e) {
		if (s > e)
			return 0;

		// 현재 구간의 넓이를 계산하자
		// 현재 구간의 최소 높이의 인덱스
		int minHeight = findMinHeight(0, n - 1, 1, s, e);
		long area = ary[minHeight] * (e - s + 1);
		int mid = minHeight;
		long left = findMax(s, mid - 1);
		long right = findMax(mid + 1, e);
		area = Math.max(area, left);
		area = Math.max(area, right);
		return area;
	}

	private static int findMinHeight(int s, int e, int idx, int l, int r) {
		if (r < s || e < l) {
			return n;
		}
		if (l <= s && e <= r) {
			return seg[idx];
		}
		int mid = (s + e) / 2;
		int left = findMinHeight(s, mid, idx * 2, l, r);
		int right = findMinHeight(mid + 1, e, idx * 2 + 1, l, r);
		if (ary[left] <= ary[right])
			return left;
		return right;
	}

	private static int init(int s, int e, int idx) {
		if (s == e) {
			return seg[idx] = s;
		}
		int mid = (s + e) / 2;
		int left = init(s, mid, idx * 2);
		int right = init(mid + 1, e, idx * 2 + 1);
		if (ary[left] <= ary[right]) {
			return seg[idx] = left;
		}
		return seg[idx] = right;
	}
}