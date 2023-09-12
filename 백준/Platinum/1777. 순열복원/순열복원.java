import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int seg[];
	static int answer[];
	static int updatedIndex = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		int ary[] = new int[n];
		seg = new int[4 * n];
		answer = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}

		init(0, n - 1, 1);
		for (int i = 0; i < n; i++) {
			//System.out.println("val : " + ary[n - 1 - i]);
			//System.out.println("n : " + (n - i));
			findIndex(0, n - 1, 1, ary[n - 1 - i], n - i);
			update(0, n - 1, 1, updatedIndex);
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<n;i++) {
			sb.append(answer[i]).append(" ");
//			System.out.print(answer[i] + " ");
		}
		System.out.println(sb);

	}

	private static int findIndex(int s, int e, int idx, int val, int n) {// val : 0,2 등, n : 8
		if (s == e) {
			answer[s] = n;
			updatedIndex = s;
			return seg[idx];
		}
		int mid = (s + e) / 2;

		if (val < seg[idx * 2 + 1]) {
			return findIndex(mid + 1, e, idx * 2 + 1, val, n);
		} else {
			return findIndex(s, mid, idx * 2, val - seg[idx * 2 + 1], n);
		}

	}

	private static int update(int s, int e, int idx, int c) {
		if (c < s || e < c) {
			return seg[idx];
		}
		if (s == e)
			return seg[idx] = 0;
		int mid = (s + e) / 2;
		int l = update(s, mid, idx * 2, c);
		int r = update(mid + 1, e, idx * 2 + 1, c);
		return seg[idx] = l + r;

	}

	private static int init(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = 1;
		int mid = (s + e) / 2;

		return seg[idx] = init(s, mid, idx * 2) + init(mid + 1, e, idx * 2 + 1);
	}
}