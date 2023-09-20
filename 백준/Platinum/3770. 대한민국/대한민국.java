import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static class Node implements Comparable<Node> {
		int a;
		int b;

		public Node(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Node o) {
			if (this.a == o.a) {
				return Integer.compare(this.b, o.b);
			} else
				return Integer.compare(this.a, o.a);
		}

	}

	static List<Node> ary;
	static final int MAX = 1000;
	static long seg[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			ary = new ArrayList<>();
			seg = new long[4 * MAX];
			for (int i = 1; i <= k; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				Node newNode = new Node(a, b);
				ary.add(newNode);
			}
			Collections.sort(ary);
			long answer = 0;
			for (int i = 0; i < k; i++) {
				int b = ary.get(i).b;

				long ans = getBigger(1, MAX, 1, b + 1, MAX);
				update(1, MAX, 1, b);
				answer += ans;
			}
			sb.append("Test case ").append(tc).append(": ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	public static long getBigger(int s, int e, int idx, int l, int r) {
		// 나보다 큰 수의 개수를 구한다
		if (r < s || e < l)
			return 0;
		if (l <= s && e <= r)
			return seg[idx];
		int mid = (s + e) / 2;
		return getBigger(s, mid, idx * 2, l, r) + getBigger(mid + 1, e, idx * 2 + 1, l, r);
	}

	public static void update(int s, int e, int idx, int c) {
		if (c < s || e < c)
			return;

		seg[idx]++;
		if (s == e)
			return;
		int mid = (s + e) / 2;
		update(s, mid, idx * 2, c);
		update(mid + 1, e, idx * 2 + 1, c);
	}

}