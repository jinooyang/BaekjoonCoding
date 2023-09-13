import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		long min;
		int idx;
		long sum;

		public Node(long num, int idx, long sum) {
			super();
			this.min = num;
			this.idx = idx;
			this.sum = sum;
		}

	}

	static long ary[];
	static Node seg[];
	static int n;
	static long answer = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		ary = new long[n];
		seg = new Node[4 * n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}

		init(0, n - 1, 1);
		getMax(0, n - 1);
		System.out.println(answer);
	}

	

	private static void getMax(int l, int r) {
		if (l > r)
			return;
		Node node = getSumAndMin(0, n - 1, 1, l, r);
		long sumMul = node.sum * node.min;
		int idx = node.idx;
		answer = Math.max(sumMul, answer);
		getMax(l, idx - 1);
		getMax(idx + 1, r);
	}

	private static Node getSumAndMin(int s, int e, int idx, int l, int r) {
		if (r < s || e < l) {
			return new Node(Integer.MAX_VALUE, 0, 0);
		}
		if (l <= s && e <= r) {
			return seg[idx];
		}
		int mid = (s + e) / 2;
		Node left = getSumAndMin(s, mid, idx * 2, l, r);
		Node right = getSumAndMin(mid + 1, e, idx * 2 + 1, l, r);

		if (left.min <= right.min) {
			return new Node(left.min, left.idx, left.sum + right.sum);
		}
		return new Node(right.min, right.idx, left.sum + right.sum);
	}

	private static Node init(int s, int e, int idx) {
		if (s == e) {
			return seg[idx] = new Node(ary[s], s, ary[s]);
		}
		int mid = (s + e) / 2;
		Node l = init(s, mid, idx * 2);
		Node r = init(mid + 1, e, idx * 2 + 1);
		if (l.min <= r.min) {
			return seg[idx] = new Node(l.min, l.idx, l.sum + r.sum);
		}
		return seg[idx] = new Node(r.min, r.idx, l.sum + r.sum);
	}
}