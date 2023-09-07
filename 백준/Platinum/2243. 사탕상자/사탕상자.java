import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int flavor;
		int cnt;

		public Node(int flavor, int cnt) {
			super();
			this.flavor = flavor;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Node [flavor=" + flavor + ", cnt=" + cnt + "]";
		}

	}

	static Node seg[];
	static final int max = 1000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		seg = new Node[4 * max + 4];
		init(1, max, 1);

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			if (a == 1) {
				int b = Integer.parseInt(st.nextToken());
				Node node = getK(1, max, 1, b);
				sb.append(node.flavor).append("\n");
				update(1, max, 1, node.flavor, -1);
			//	printseg();

			}
			if (a == 2) {
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				update(1, max, 1, b, c);
			//	printseg();

			}
		}
		System.out.println(sb.toString());
	}

	private static void printseg() {
		for (int k = 0; k < 4 * max; k++) {
			System.out.print(seg[k] + " ");
		}
		System.out.println();
	}

	private static Node getK(int s, int e, int idx, int k) {

		if (s == e) {
			return seg[idx];
		}
		if (seg[idx].cnt == k) {
			return seg[idx];
		}
		int mid = (s + e) / 2;
		if (seg[idx * 2].cnt < k) {
			// 오른쪽에서 찾자
			return getK(mid + 1, e, idx * 2 + 1, k - seg[idx * 2].cnt);
		} else {
			// 왼쪽에서 찾자
			return getK(s, mid, idx * 2, k);
		}

	}

	private static Node update(int s, int e, int idx, int c, int val) {
		//System.out.println(s + " " + e);
		if (c < s || e < c) {
			return seg[idx];
		}

		if (s == e) {
		//	System.out.println("update : " + s);
			seg[idx].cnt += val;
			return seg[idx];
		}
		int mid = (s + e) / 2;
		Node left = update(s, mid, idx * 2, c, val);
		Node right = update(mid + 1, e, idx * 2 + 1, c, val);

		if (right.cnt == 0 && left.cnt == 0) {
			return seg[idx] = new Node(0, 0);
		}
		if (right.cnt == 0) {
			return seg[idx] = new Node(left.flavor, left.cnt);
		}
		if (left.cnt == 0) {
			return seg[idx] = new Node(right.flavor, right.cnt);
		}

		return seg[idx] = new Node(right.flavor, left.cnt + right.cnt);

	}

	private static Node init(int s, int e, int idx) {
		if (s == e) {
			return seg[idx] = new Node(s, 0);
		}
		int mid = (s + e) / 2;
		Node left = init(s, mid, idx * 2);
		Node right = init(mid + 1, e, idx * 2 + 1);
		if (right.cnt == 0 && left.cnt == 0) {
			return seg[idx] = new Node(0, 0);
		}
		if (right.cnt == 0) {
			return seg[idx] = new Node(left.flavor, left.cnt);
		}
		if (left.cnt == 0) {
			return seg[idx] = new Node(right.flavor, right.cnt);
		}
		// 둘다 있을 경우
		return seg[idx] = new Node(right.flavor, left.cnt + right.cnt);
	}
}