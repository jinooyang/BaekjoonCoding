import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		long a;
		long b;

		public Node(long a, long b) {
			super();
			this.a = a;
			this.b = b;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int A[] = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int B[] = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		int segA[] = new int[4 * n];
		int segB[] = new int[4 * m];

		init(0, n - 1, 1, segA, A);
		init(0, m - 1, 1, segB, B);
		Map<Integer, Node> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				int sum = getSum(0, n - 1, 1, segA, i, j);
				if (!map.containsKey(T - sum)) {
					map.put(T - sum, new Node(0, 0));
				}
				map.get(T - sum).a++;
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = i; j < m; j++) {
				int sum = getSum(0, m - 1, 1, segB, i, j);
				if (map.containsKey(sum)) {
					map.get(sum).b++;
				}
			}
		}
		long answer = 0;
		for (Integer i : map.keySet()) {
			Node node = map.get(i);
			answer += node.a * node.b;
		}
		System.out.println(answer);

	}

	private static int getSum(int s, int e, int idx, int[] seg, int l, int r) {
		if (r < s || e < l) {
			return 0;
		}
		if (l <= s && e <= r) {
			return seg[idx];
		}
		int mid = (s + e) / 2;
		return getSum(s, mid, idx * 2, seg, l, r) + getSum(mid + 1, e, idx * 2 + 1, seg, l, r);
	}

	private static int init(int s, int e, int idx, int[] seg, int[] ary) {
		if (s == e) {
			return seg[idx] = ary[s];
		}
		int mid = (s + e) / 2;
		return seg[idx] = init(s, mid, idx * 2, seg, ary) + init(mid + 1, e, idx * 2 + 1, seg, ary);
	}
}