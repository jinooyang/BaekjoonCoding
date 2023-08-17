import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long seg[];
	static long ary[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		ary = new long[N];
		seg = new long[4 * N + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			ary[i] = Long.parseLong(st.nextToken());
		}
		segTree(0, N - 1, 1);
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			if (a == 1) {
				long diff = c - ary[b - 1];
				update(0, N - 1, 1, b - 1, diff);
				ary[b - 1] = c;
			}
			if (a == 2) {
				long sum = getSum(0, N - 1, 1, b - 1, c - 1);
				System.out.println(sum);
			}
		}
	}

	private static long getSum(int start, int end, int node, int left, long right) {
		if (left > end || right < start)
			return 0;
		if (left <= start && right >= end)
			return seg[node];
		int mid = (start + end) / 2;
		return getSum(start, mid, node * 2, left, right) + getSum(mid + 1, end, node * 2 + 1, left, right);
	}

	private static void update(int start, int end, int node, int changeidx, long diff) {
		if (changeidx < start || changeidx > end)
			return;
		seg[node] += diff;
		if (start == end)
			return;
		int mid = (start + end) / 2;
		update(start, mid, node * 2, changeidx, diff);
		update(mid + 1, end, node * 2 + 1, changeidx, diff);
	}

	private static long segTree(int start, int end, int node) {
		if (start == end)
			return seg[node] = ary[start];
		int mid = (start + end) / 2;
		return seg[node] = segTree(start, mid, node * 2) + segTree(mid + 1, end, 2 * node + 1);
	}
}