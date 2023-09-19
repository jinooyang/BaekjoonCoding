import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static class Node {
		int index;
		int cnt;

		public Node(int index, int cnt) {
			super();
			this.index = index;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Node [index=" + index + ", cnt=" + cnt + "]";
		}

	}

	public static Node seg[];
	public static int ary[];
	public static int ary2[];
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		seg = new Node[4 * n];
		ary = new int[n + 1];
		ary2 = new int[n + 1];

		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			ary[i] = x;
			ary2[i] = x;
		}
		Arrays.sort(ary2);
		init(1, n, 1);

		for (int i = 1; i <= n; i++) {
			cnt = 0;
			getK(1, n, 1, ary[i]);
			update(1, n, 1, ary[i]);

			sb.append(i - cnt).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static Node getK(int s, int e, int idx, int k) {
		if (s == e)
			return seg[idx];
		Node l = seg[idx * 2];
		int mid = (s + e) / 2;

		if (k < l.index) {
			return getK(s, mid, idx * 2, k);
		}
		cnt += l.cnt;
		return getK(mid + 1, e, idx * 2 + 1, k);
	}

	public static Node update(int s, int e, int idx, int x) {
		if (x < ary2[s] || ary2[e] < x)
			return seg[idx];
		if (s == e) {
			seg[idx].cnt++;
			return seg[idx];
		}
		int mid = (s + e) / 2;
		Node l = update(s, mid, idx * 2, x);
		Node r = update(mid + 1, e, idx * 2 + 1, x);
		if (r.cnt == 0 && l.cnt == 0) {
			return seg[idx] = new Node(0, 0);
		}
		if (r.cnt == 0) {
			return seg[idx] = new Node(l.index, l.cnt);
		}
		return seg[idx] = new Node(r.index, l.cnt + r.cnt);
	}

	public static Node init(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = new Node(ary2[s], 0);
		int mid = (s + e) / 2;
		Node l = init(s, mid, idx * 2);
		Node r = init(mid + 1, e, idx * 2 + 1);
		if (r.cnt == 0 && l.cnt == 0) {
			return seg[idx] = new Node(0, 0);
		}
		if (r.cnt == 0) {
			return seg[idx] = new Node(l.index, l.cnt);
		}
		return seg[idx] = new Node(r.index, l.cnt + r.cnt);
	}

}