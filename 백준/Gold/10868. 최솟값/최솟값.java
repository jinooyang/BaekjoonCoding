import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int ary[];
	static int seg[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		ary = new int[N];
		seg = new int[N * 4];
		Arrays.fill(seg, Integer.MAX_VALUE);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			ary[i] = Integer.parseInt(st.nextToken());
		}
		init(0, N - 1, 1);
//		for (int i = 0; i < 4 * N; i++) {
//			System.out.print(seg[i] + " ");
//		}
//		System.out.println();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			sb.append(findMin(0, N - 1, 1, Math.min(l, r) - 1, Math.max(l, r) - 1)).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int findMin(int s, int e, int idx, int l, int r) {
		if (r < s || e < l) {
			// 완전 범위 밖이면
			return Integer.MAX_VALUE;
		}
		if (l <= s && e <= r)
			return seg[idx];
		if (s == e) {
			return seg[idx];
		}
		int mid = (s + e) / 2;
		return Math.min(findMin(s, mid, idx * 2, l, r), findMin(mid + 1, e, idx * 2 + 1, l, r));
	}

	private static int init(int s, int e, int idx) {
		if (s == e) {
			return seg[idx] = ary[s];
		}
		int mid = (s + e) / 2;
		return seg[idx] = Math.min(init(s, mid, idx * 2), init(mid + 1, e, idx * 2 + 1));
	}
}
