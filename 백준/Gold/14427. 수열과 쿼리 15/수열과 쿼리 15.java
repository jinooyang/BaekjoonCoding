import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int num;
		int index;

		public Node(int num, int index) {
			super();
			this.num = num;
			this.index = index;
		}

		public Node(Node n) {
			this.num = n.num;
			this.index = n.index;
		}

		@Override
		public int compareTo(Node o) {
			if (this.num != o.num)
				return Integer.compare(this.num, o.num);
			else
				return Integer.compare(this.index, o.index);
		}

		@Override
		public String toString() {
			return "Node [num=" + num + ", index=" + index + "]";
		}

	}

	static int ary[];
	static Node seg[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
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

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int flag = Integer.parseInt(st.nextToken());
			if (flag == 1) {
				int idx = Integer.parseInt(st.nextToken()) - 1;
				int val = Integer.parseInt(st.nextToken());
				update(0, n - 1, 1, idx, val);
			}
			if (flag == 2) {

				sb.append(seg[1].index + 1).append("\n");

			}
		}
		System.out.println(sb.toString());
	}

	private static Node update(int s, int e, int idx, int c, int val) {
		if (c < s || e < c) {
			return seg[idx];
		}
		if (s == e) {
			seg[idx].num = val;
			return seg[idx];
		}
		int mid = (s + e) / 2;
		Node left = update(s, mid, idx * 2, c, val);
		Node right = update(mid + 1, e, idx * 2 + 1, c, val);
		if (left.compareTo(right) < 0) {
			seg[idx].num = left.num;
			seg[idx].index = left.index;
			return seg[idx];
		}

		seg[idx].num = right.num;
		seg[idx].index = right.index;
		return seg[idx];
	}

	private static Node init(int s, int e, int idx) {
		if (s == e) {
			return seg[idx] = new Node(ary[s], s);
		}
		int mid = (s + e) / 2;
		Node left = init(s, mid, idx * 2);
		Node right = init(mid + 1, e, idx * 2 + 1);
		if (left.compareTo(right) < 0) {
			return seg[idx] = new Node(left);
		}
		return seg[idx] = new Node(right);
	}
}