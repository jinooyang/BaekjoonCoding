import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static class Node {
		//구간의 합
		int sum;

		//구간의 최대 구간합
		int max;

		//왼쪽끝을 포함한 구간의 최대 합
		int leftMax;

		//오른쪽 끝을 포함한 구간의 최대합
		int rightMax;

		//생성자

		public Node(int sum, int max, int leftMax, int rightMax) {
			this.sum = sum;
			this.max = max;
			this.leftMax = leftMax;
			this.rightMax = rightMax;
		}

		@Override
		public String toString() {
			return "Node{" +
				"sum=" + sum +
				", max=" + max +
				", leftMax=" + leftMax +
				", rightMax=" + rightMax +
				'}';
		}
	}

	public static int U;
	public static int V;
	public static Node[] seg;
	public static int[] ary;

	public static final int MIN = -200_000_000;

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		seg = new Node[4 * N];
		ary = new int[N + 1];
		int Q = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}
		init(1, N, 1);
		// printSeg(N);
		StringBuilder sb = new StringBuilder();
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int C = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			if (C == 0) {
				//구간합의 최대를 구한다
				//seg[1].max
				sb.append(getMax(1, N, 1, A, B).max).append("\n");
			} else {
				//updateSeg
				update(1, N, 1, A, B);
				// printSeg(N);
			}
		}
		System.out.println(sb);
	}

	private static void printSeg(int N) {
		System.out.println("----PRINT SEGTREE----");
		for (int i = 1; i < 4 * N; i++) {
			if (seg[i] == null)
				break;
			System.out.println(seg[i].toString());
		}
		System.out.println();
	}

	//public Node(int sum, int max, int leftMax, int rightMax) {

	private static Node getMax(int s, int e, int idx, int l, int r) {
		if (r < s || e < l) {
			return new Node(0, MIN, MIN, MIN);
		}
		if (l <= s && e <= r) {
			return seg[idx];
		}
		int mid = (s + e) >> 1;
		Node left = getMax(s, mid, idx * 2, l, r);
		Node right = getMax(mid + 1, e, idx * 2 + 1, l, r);
		return new Node(left.sum + right.sum,
			Math.max(Math.max(left.max, right.max), left.rightMax + right.leftMax + V),
			Math.max(left.leftMax, left.sum + right.leftMax + V),
			Math.max(right.rightMax, right.sum + left.rightMax + V));
	}

	private static void update(int s, int e, int idx, int c, int val) {
		if (c < s || e < c)
			return;
		if (s == e) {
			seg[idx].sum = U * val;
			seg[idx].max = U * val;
			seg[idx].leftMax = U * val;
			seg[idx].rightMax = U * val;
			return;
		}
		int mid = (s + e) >> 1;
		update(s, mid, idx * 2, c, val);
		update(mid + 1, e, idx * 2 + 1, c, val);
		Node left = seg[idx * 2];
		Node right = seg[idx * 2 + 1];
		changeSegNode(idx, left, right);
	}

	private static Node init(int s, int e, int idx) {
		if (s == e)
			return seg[idx] =
				new Node(ary[s] * U, ary[s] * U, ary[s] * U, ary[s] * U);
		int mid = (s + e) >> 1;
		Node leftNode = init(s, mid, idx * 2);
		Node rightNode = init(mid + 1, e, idx * 2 + 1);
		seg[idx] = new Node(MIN, MIN, MIN, MIN);
		changeSegNode(idx, leftNode, rightNode);
		return seg[idx];
	}

	private static void changeSegNode(int idx, Node leftNode, Node rightNode) {
		//segIdx값을 바꾼다
		seg[idx].sum = leftNode.sum + rightNode.sum + V;//두 수를 잇는 V값

		//seg[idx].max는 아래 3개의 경우 중 가장 큰 값.
		int maxL = leftNode.max;
		int maxR = rightNode.max;
		int maxLR = leftNode.rightMax + rightNode.leftMax + V;
		seg[idx].max = Math.max(Math.max(maxL, maxR), maxLR);

		seg[idx].leftMax = Math.max(leftNode.leftMax, leftNode.sum + rightNode.leftMax + V);
		seg[idx].rightMax = Math.max(rightNode.rightMax, rightNode.sum + leftNode.rightMax + V);
	}

}