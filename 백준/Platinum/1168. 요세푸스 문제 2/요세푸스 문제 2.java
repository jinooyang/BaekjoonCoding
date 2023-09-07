import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int idx;
		int cnt;

		public Node(int idx, int cnt) {
			super();
			this.idx = idx;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Node [idx=" + idx + ", cnt=" + cnt + "]";
		}

	}

	static int n;
	static int m;
	static Node seg[];
	static int answer[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());// 7
		m = Integer.parseInt(st.nextToken());// 3

		seg = new Node[4 * n];
		answer = new int[n + 1];
		init(1, n, 1);

		// printtree();
		int start = m;
		for (int i = 1; i <= n; i++) {
			int nextidx = start % (n - i + 1);
			if (nextidx == 0)
				nextidx = n - i + 1;
			// System.out.println("start : " + start);
			// System.out.println("nextIdx : " + nextidx);
			Node node = getCnt(1, n, 1, nextidx);
			answer[i] = node.idx;
			// System.out.println(answer[i]);
			update(1, n, 1, node.idx);

			// printtree();
			start = nextidx;
			start += m - 1;

		}
		StringBuilder sb = new StringBuilder();
		sb.append("<");

		for (int i = 1; i < n; i++) {
			sb.append(answer[i]).append(", ");
		}
		sb.append(answer[n]).append(">");
		System.out.println(sb);

	}

	private static void printtree() {
		for (int i = 1; i < 4 * n; i++) {
			System.out.print(seg[i] + " ");
		}
		System.out.println();
	}

	private static Node getCnt(int s, int e, int idx, int cnt) {

		if (s == e)
			return seg[idx];
		int mid = (s + e) / 2;

		if (seg[idx * 2].cnt == 0 && seg[idx * 2 + 1].cnt == 0) {
			return seg[idx];
		}
		if (seg[idx * 2].cnt == 0) {
			return getCnt(mid + 1, e, idx * 2 + 1, cnt - seg[idx * 2].cnt);// right
		}
		if (seg[idx * 2 + 1].cnt == 0) {
			return getCnt(s, mid, idx * 2, cnt);// left
		}

		if (seg[idx].cnt == cnt) {
			return seg[idx];
		} else if (seg[idx * 2].cnt >= cnt) {
			return getCnt(s, mid, idx * 2, cnt);// left
		} else {
			return getCnt(mid + 1, e, idx * 2 + 1, cnt - seg[idx * 2].cnt);
		}
	}

	private static Node update(int s, int e, int idx, int c) {
		if (c < s || e < c) {
			return seg[idx];
		}
		if (s == e) {
			seg[idx].idx = 0;
			seg[idx].cnt = 0;
			return seg[idx];
		}
		int mid = (s + e) / 2;
		Node left = update(s, mid, idx * 2, c);
		Node right = update(mid + 1, e, idx * 2 + 1, c);
		if (right.idx >= left.idx) {
			return seg[idx] = new Node(right.idx, right.cnt + left.cnt);
		}
		return seg[idx] = new Node(left.idx, right.cnt + left.cnt);
	}

	private static Node init(int s, int e, int idx) {
		if (s == e) {
			return seg[idx] = new Node(s, 1);
		}
		int mid = (s + e) / 2;
		Node left = init(s, mid, idx * 2);
		Node right = init(mid + 1, e, idx * 2 + 1);
		if (right.idx >= left.idx) {
			return seg[idx] = new Node(right.idx, right.cnt + left.cnt);
		}
		return seg[idx] = new Node(left.idx, right.cnt + left.cnt);

	}
}
