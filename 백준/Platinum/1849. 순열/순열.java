import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int seg[];
	static int answer[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		seg = new int[4 * n];
		answer = new int[n + 1];
		init(1, n, 1);
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int index = findIndex(1, n, 1, x);
			answer[index] = i;
			update(1, n, 1, index);
		}
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=n;i++) {
			sb.append(answer[i]).append("\n");
		}
		System.out.println(sb);
	}

	private static int findIndex(int s, int e, int idx, int k) {
		if (s == e)
			return s;
		int l = seg[idx * 2];
		int mid = (s + e) / 2;
		if (l > k) {
			return findIndex(s, mid, idx * 2, k);
		}
		return findIndex(mid + 1, e, idx * 2 + 1, k - l);
	}

	private static void update(int s, int e, int idx, int c) {
		if (c < s || e < c)
			return;
		seg[idx] -= 1;
		if (s == e)
			return;
		int mid = (s + e) / 2;
		update(s, mid, idx * 2, c);
		update(mid + 1, e, idx * 2 + 1, c);
	}

	private static int init(int s, int e, int idx) {
		if (s == e) {
			return seg[idx] = 1;
		}
		int mid = (s + e) / 2;
		return seg[idx] = init(s, mid, idx * 2) + init(mid + 1, e, idx * 2 + 1);

	}
}