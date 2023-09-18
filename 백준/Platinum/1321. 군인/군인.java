import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int index;// 부대 번호
		int size;// 부대 인원

		public Node(int index, int size) {
			super();
			this.index = index;
			this.size = size;
		}

	}

	static int ary[];
	static Node seg[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		ary = new int[n + 1];
		seg = new Node[4 * n];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}
		init(1, n, 1);

		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		for (int q = 0; q < m; q++) {
			st = new StringTokenizer(br.readLine());
			int flag = Integer.parseInt(st.nextToken());
			if (flag == 1) {
				// i번 부대에 a명을 더한다는 뜻이다.
				int i = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				update(1, n, 1, i, a);
			}
			if (flag == 2) {
				// i번 군인이 몇 번 부대에 배치 받았는지를 출력
				int i = Integer.parseInt(st.nextToken());
				Node node = query(1, n, 1, i);
				sb.append(node.index).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static Node query(int s, int e, int idx, int i) {
		if (s == e)
			return seg[idx];
		Node l = seg[idx * 2];
		int mid = (s + e) / 2;
		if (i > l.size) {
			return query(mid + 1, e, idx * 2 + 1, i - l.size);
		} else {
			return query(s, mid, idx * 2, i);
		}
	}

	private static void update(int s, int e, int idx, int c, int val) {
		if (c < s || e < c)
			return;
		seg[idx].size += val;
		if (s == e)
			return;
		int mid = (s + e) / 2;
		update(s, mid, idx * 2, c, val);
		update(mid + 1, e, idx * 2 + 1, c, val);
		Node l = seg[idx * 2];
		Node r = seg[idx * 2 + 1];
		if (r.size != 0) {
			seg[idx].index = r.index;
			seg[idx].size = l.size + r.size;
		} else {
			seg[idx].index = l.index;
			seg[idx].size = l.size;
		}
	}

	private static Node init(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = new Node(s, ary[s]);

		int mid = (s + e) / 2;
		Node l = init(s, mid, idx * 2);
		Node r = init(mid + 1, e, idx * 2 + 1);
		if (r.size != 0) {
			return seg[idx] = new Node(r.index, l.size + r.size);
		}
		return seg[idx] = new Node(l.index, l.size);

	}
}