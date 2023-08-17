import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long ary[];
	static long seg[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		ary = new long[n];
		seg = new long[4 * n + 1];
		init(0, n - 1, 1);
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if (a == 0) {
				int small = Math.min(b, c);
				int big = Math.max(b, c);
				long sum = sum(0, n - 1, 1, small - 1, big - 1);
				sb.append(sum).append("\n");
			}
			if (a == 1) {
				long diff = c - ary[b - 1];
				update(0, n - 1, 1, b - 1, diff);
				ary[b - 1] = c;
			}
		}
		System.out.println(sb.toString());
	}

	private static long sum(int start, int end, int node, long left, long right) {
		if (left > end || start > right)
			return 0;
		if (left <= start && right >= end)
			return seg[node];
		int mid = (start + end) / 2;
		return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
	}

	private static long init(int start, int end, int node) {
		if (start == end) {
			return seg[node] = ary[start];
		}
		int mid = (start + end) / 2;
		return seg[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);

	}

	private static void update(int start, int end, int node, long change, long diff) {
		if (start > change || end < change)
			return;
		seg[node] += diff;
		if (start == end)
			return;
		int mid = (start + end) / 2;
		update(start, mid, node * 2, change, diff);
		update(mid + 1, end, node * 2 + 1, change, diff);
	}
}