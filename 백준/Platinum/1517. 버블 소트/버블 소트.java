import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long ary[];
	static Node seg[];

	private static class Node {
		long num;
		int index; // segtree가 찾아줄거임
		long size; // segtree가 사용할거임
		long swap;

		public Node(long num, int index, long size, long swap) {
			super();
			this.num = num;
			this.index = index;
			this.size = size;
			this.swap = swap;
		}

		@Override
		public String toString() {
			return "[" + num + ", " + size + ", " + swap + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		ary = new long[n];
		seg = new Node[4 * n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			long x = Long.parseLong(st.nextToken());
			ary[i] = x;
		}
		init(0, n - 1, 1);// 세그 트리 생성
		// System.out.println(Arrays.toString(seg));

		long answer = 0;
		int nextUpdateIdx = 0;
		for (int k = 1; k <= n; k++) {
			answer += seg[1].swap;
			// System.out.println(answer);
			nextUpdateIdx = seg[1].index;
			update(0, n - 1, 1, nextUpdateIdx);
			// System.out.println(Arrays.toString(seg));
			// segtree를 업데이트 하자. result.index에 있는 값을 0으로 바꾸고 그녀석의 사이즈를 수정해준다
		}
		System.out.println(answer);
	}

	private static Node update(int s, int e, int n, int idx) {
		// 범위 밖에 있으면 볼 필요가 없다
		if (idx < s || e < idx)
			return seg[n];
		// 범위 안에 수정을 해야함
		if (s == e) {
			seg[n].num = Integer.MIN_VALUE;
			seg[n].size = 0;
			return seg[n];
		}
		int mid = (s + e) / 2;
		Node leftSubtree = update(s, mid, 2 * n, idx);
		Node rightSubtree = update(mid + 1, e, 2 * n + 1, idx);
		if (leftSubtree.num > rightSubtree.num) {// swap이 일어난다//left가 오른쪽을 잡는다
			return seg[n] = new Node(leftSubtree.num, leftSubtree.index, rightSubtree.size + leftSubtree.size,
					leftSubtree.swap + rightSubtree.size);

		} else {// 오른쪽이 그대로 올라온다
			return seg[n] = new Node(rightSubtree.num, rightSubtree.index, rightSubtree.size + leftSubtree.size,
					rightSubtree.swap);
		}

	}

	private static Node init(int s, int e, int n) {// 세그 트리 생성
		if (s == e)
			return seg[n] = new Node(ary[s], s, 1, 0);
		int mid = (s + e) / 2;
		Node leftSubtree = init(s, mid, 2 * n);
		Node rightSubtree = init(mid + 1, e, 2 * n + 1);
		if (leftSubtree.num > rightSubtree.num) {// swap이 일어난다
			return seg[n] = new Node(leftSubtree.num, leftSubtree.index, rightSubtree.size + leftSubtree.size,
					leftSubtree.swap + rightSubtree.size);

		} else {// 오른쪽이 그대로 올라온다
			return seg[n] = new Node(rightSubtree.num, rightSubtree.index, rightSubtree.size + leftSubtree.size,
					rightSubtree.swap);
		}

	}
}