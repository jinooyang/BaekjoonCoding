import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int seg[];
	static List<Node> ary = new ArrayList<>();

	static class Node implements Comparable<Node> {
		int num;
		int index;

		public Node(int num, int index) {
			super();
			this.num = num;
			this.index = index;
		}

		@Override
		public int compareTo(Node o) {

			return Integer.compare(o.num, this.num);
		}

	}

	public static void main(String[] args) throws IOException {
	//	System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		seg = new int[4 * N];
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			ary.add(new Node(x, i));
		}
		Collections.sort(ary);
		int unbalanced = 0;
		for (int i = 0; i < N; i++) {
			int num = ary.get(i).num;
			int index = ary.get(i).index;

			int L = 0;
			if (index - 1 >= 0)
				L = getSum(0, N - 1, 1, 0, index - 1);
			int R = 0;
			if (index + 1 <= N - 1)
				R = getSum(0, N - 1, 1, index + 1, N);
			int max = Math.max(L, R);
			int min = Math.min(L, R);
			if (max > min * 2) {
				unbalanced++;
//				System.out.println(num);
			}
//			System.out.println("num : " + num + ", L : " + L + ", R : " + R);
			update(0, N - 1, 1, index);
		}
		System.out.println(unbalanced);
	}

	private static int getSum(int s, int e, int idx, int l, int r) {
		if (r < s || e < l)
			return 0;
		if (l <= s && e <= r)
			return seg[idx];
		int mid = (s + e) >> 1;

		return getSum(s, mid, idx * 2, l, r) + getSum(mid + 1, e, idx * 2 + 1, l, r);
	}

	private static void update(int s, int e, int idx, int c) {
		if (c < s || e < c)
			return;
		if (s == e) {
			seg[idx] = 1;
			return;
		}

		int mid = (s + e) >> 1;
		update(s, mid, idx * 2, c);
		update(mid + 1, e, idx * 2 + 1, c);
		seg[idx] = seg[idx * 2] + seg[idx * 2 + 1];
	}

}