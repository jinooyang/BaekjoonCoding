import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.FileInputStream;

public class Main {

	static class Node {
		long sum;
		int cnt;

		public Node(long sum, int cnt) {
			super();
			this.sum = sum;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Node [sum=" + sum + ", cnt=" + cnt + "]";
		}

	}

	static Node seg[];
	static final int MAX_SIZE = 200_000;
	static final long MOD = 1_000_000_007;
	static Node zero = new Node(0, 0);

	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		seg = new Node[4 * MAX_SIZE];
		for (int i = 0; i < 4 * MAX_SIZE; i++) {
			seg[i] = new Node(0, 0);
		}
		long answer = 1;
		for (int i = 0; i < n; i++) {
			int newTree = Integer.parseInt(br.readLine());

			if (i != 0) {
				Node left = getSumAndCnt(0, MAX_SIZE, 1, 0, newTree - 1);
				Node right = getSumAndCnt(0, MAX_SIZE, 1, newTree + 1, MAX_SIZE);

				long leftCost = ((long)newTree * left.cnt) - (left.sum) % MOD;
				long rightCost = (right.sum) - ((long)newTree * right.cnt) % MOD;
				long cost = leftCost % MOD + rightCost % MOD;

				answer *= cost % MOD;
				answer %= MOD;
			}

			update(0, MAX_SIZE, 1, newTree);

		}
		System.out.println(answer);

	}

	private static Node getSumAndCnt(int s, int e, int idx, int l, int r) {
//		System.out.println(s + " " + e);
		if (r < s || e < l)
			return zero;
		if (l <= s && e <= r)
			return seg[idx];
		int mid = (s + e) >> 1;
		Node left = getSumAndCnt(s, mid, idx * 2, l, r);
		Node right = getSumAndCnt(mid + 1, e, idx * 2 + 1, l, r);

		return new Node(left.sum + right.sum, left.cnt + right.cnt);
	}

	private static void update(int s, int e, int idx, int c) {
		if (c < s || e < c)
			return;
		if (s == e) {
			seg[idx].sum += c;
			seg[idx].cnt += 1;
			return;
		}
		int mid = (s + e) >> 1;
		update(s, mid, idx * 2, c);
		update(mid + 1, e, idx * 2 + 1, c);
		seg[idx].sum = seg[idx * 2].sum + seg[idx * 2 + 1].sum;
		seg[idx].cnt = seg[idx * 2].cnt + seg[idx * 2 + 1].cnt;
	}

}