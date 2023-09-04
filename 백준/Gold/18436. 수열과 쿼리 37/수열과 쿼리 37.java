import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static class Node {
		int even;
		int odd;

		public Node(int even, int odd) {
			super();
			this.even = even;
			this.odd = odd;
		}

	}

	static int ary[];
	static Node seg[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());

		ary = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}
		seg = new Node[4 * n];

		init(0, n - 1, 1);

		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		for (int k = 0; k < m; k++) {
			st = new StringTokenizer(br.readLine());
			int flag = Integer.parseInt(st.nextToken());
			if (flag == 1) {
				int i = Integer.parseInt(st.nextToken()) - 1;
				int x = Integer.parseInt(st.nextToken());
				update(0, n - 1, 1, i, x);
			}
			if (flag == 2) {
				int l = Integer.parseInt(st.nextToken()) - 1;
				int r = Integer.parseInt(st.nextToken()) - 1;
				Node node = get(0, n - 1, 1, l, r);
				sb.append(node.even).append("\n");
			}
			if (flag == 3) {
				int l = Integer.parseInt(st.nextToken()) - 1;
				int r = Integer.parseInt(st.nextToken()) - 1;
				Node node = get(0, n - 1, 1, l, r);
				sb.append(node.odd).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static Node get(int s, int e, int idx, int l, int r) {
		if (r < s || e < l) {
			return new Node(0, 0);
		}
		if (l <= s && e <= r) {
			return seg[idx];
		}
		int mid = (s + e) / 2;
		Node left = get(s, mid, idx * 2, l, r);
		Node right = get(mid + 1, e, idx * 2 + 1, l, r);
		return new Node(left.even + right.even, left.odd + right.odd);
	}

	private static Node update(int s, int e, int idx, int c, int val) {
		if (c < s || e < c) {
			return seg[idx];
		}
		if (s == e) {
			if (val % 2 == 0) {
				seg[idx].even = 1;
				seg[idx].odd = 0;
				return seg[idx];
			}
			if (val % 2 == 1) {
				seg[idx].even = 0;
				seg[idx].odd = 1;
				return seg[idx];
			}
		}

		int mid = (s + e) / 2;
		Node left = update(s, mid, idx * 2, c, val);
		Node right = update(mid + 1, e, idx * 2 + 1, c, val);
		seg[idx].even = left.even + right.even;
		seg[idx].odd = left.odd + right.odd;
		return seg[idx];
	}

	private static Node init(int s, int e, int idx) {

		if (s == e) {
			if (ary[s] % 2 == 0) {// 짝수일 경우
				return seg[idx] = new Node(1, 0);
			}
			if (ary[s] % 2 == 1) {
				return seg[idx] = new Node(0, 1);
			}
		}
		int mid = (s + e) / 2;
		Node left = init(s, mid, idx * 2);
		Node right = init(mid + 1, e, idx * 2 + 1);
		return seg[idx] = new Node(left.even + right.even, left.odd + right.odd);

	}
}