import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int MIN = -200_000_000;
	static class Node {
		int leftSum;
		int rightSum;
		int maxSum;
		int sum;

		public Node(int leftSum, int rightSum, int maxSum, int sum) {
			super();
			this.leftSum = leftSum;
			this.rightSum = rightSum;
			this.maxSum = maxSum;
			this.sum = sum;
		}

		public Node() {
			super();
			this.leftSum = MIN;
			this.rightSum = MIN;
			this.maxSum = MIN;
			this.sum = 0;
		}

	}

	static int N;
	static int ary[];
	static Node seg[];

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		ary = new int[N + 1];
		seg = new Node[N * 4];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}
		init(1, N, 1);
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int Q = Integer.parseInt(st.nextToken());
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int ans = getMax(1, N, 1, i, j).maxSum;
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static Node getMax(int s, int e, int idx, int l, int r) {
		if (r < s || e < l) {
			return new Node();
		}
		if (l <= s && e <= r)
			return seg[idx];
		int mid = (s + e) >> 1;
		Node left = getMax(s, mid, idx * 2, l, r);
		Node right = getMax(mid + 1, e, idx * 2 + 1, l, r);
		Node node = new Node();
		node.leftSum = Math.max(left.leftSum, left.sum + right.leftSum);
		node.rightSum = Math.max(right.rightSum, right.sum + left.rightSum);
		int max = Math.max(left.maxSum, right.maxSum);
		max = Math.max(max, left.rightSum + right.leftSum);
		node.maxSum = max;
		node.sum = left.sum + right.sum;
		return node;
	}

	public static Node init(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = new Node(ary[s], ary[s], ary[s], ary[s]);
		int mid = (s + e) >> 1;
		Node left = init(s, mid, idx * 2);
		Node right = init(mid + 1, e, idx * 2 + 1);
		Node node = new Node();
		node.leftSum = Math.max(left.leftSum, left.sum + right.leftSum);
		node.rightSum = Math.max(right.rightSum, right.sum + left.rightSum);
		int max = Math.max(left.maxSum, right.maxSum);
		max = Math.max(max, left.rightSum + right.leftSum);
		node.maxSum = max;
		node.sum = left.sum + right.sum;
		return seg[idx] = node;
	}

}