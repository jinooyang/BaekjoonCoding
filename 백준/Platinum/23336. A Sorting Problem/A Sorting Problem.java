import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int max;
		int cnt;
		int idx;

		public Node(int max, int cnt, int idx) {

			this.max = max;
			this.cnt = cnt;
			this.idx = idx;
		}
	}

	static Node seg[];
	static int ary[];

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		ary = new int[N];
		seg = new Node[4 * N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}

		init(0, N - 1, 1);
		long answer = 0;
		for (int i = 0; i < N; i++) {

			Node max = getNode(0, N - 1, 1, 0, N - 1);
			// System.out.println("max : " + max.max);
			Node right = getNode(0, N - 1, 1, max.idx + 1, N - 1);
			// System.out.println("right cnt : " + right.cnt);
			answer += right.cnt;
			update(0, N - 1, 1, max.idx, 0);
		}
		System.out.println(answer);

	}
	//public Node(int max, int cnt, int idx) {

	private static Node getNode(int s, int e, int idx, int l, int r) {
		if (r < l)
			return new Node(0, 0, 0);
		//now 보다 max가 작거나 같은 경우
		if (r < s || e < l)
			return new Node(0, 0, 0);
		if (l <= s && e <= r) {
			return seg[idx];

		}

		int mid = (s + e) >> 1;
		Node left = getNode(s, mid, idx * 2, l, r);
		Node right = getNode(mid + 1, e, idx * 2 + 1, l, r);
		Node node = new Node(0, 0, 0);

		if (left.max > right.max) {
			node.max = left.max;
			node.idx = left.idx;
			node.cnt = left.cnt + right.cnt;
		} else {
			node.max = right.max;
			node.idx = right.idx;
			node.cnt = left.cnt + right.cnt;
		}
		return node;

	}

	private static void update(int s, int e, int idx, int c, int val) {
		if (c < s || e < c)
			return;
		if (s == e) {
			seg[idx].max = val;
			seg[idx].cnt = 0;
			return;
		}
		int mid = (s + e) >> 1;
		update(s, mid, idx * 2, c, val);
		update(mid + 1, e, idx * 2 + 1, c, val);
		Node left = seg[idx * 2];
		Node right = seg[idx * 2 + 1];
		seg[idx].cnt = left.cnt + right.cnt;
		seg[idx].max = Math.max(left.max, right.max);
		seg[idx].idx = left.max > right.max? left.idx : right.idx;

	}

	private static Node init(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = new Node(ary[s], 1, s);

		int mid = (s + e) >> 1;
		Node left = init(s, mid, idx * 2);
		Node right = init(mid + 1, e, idx * 2 + 1);

		seg[idx] = new Node(0, 0, 0);
		if (left.max > right.max) {
			seg[idx].max = left.max;
			seg[idx].idx = left.idx;
			seg[idx].cnt = left.cnt + right.cnt;
			return seg[idx];
		}
		seg[idx].max = right.max;
		seg[idx].idx = right.idx;
		seg[idx].cnt = left.cnt + right.cnt;
		return seg[idx];

	}

}