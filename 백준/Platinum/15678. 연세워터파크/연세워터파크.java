import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long ary[];
	static long seg[];
	static long dp[];

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		ary = new long[N + 1];
		seg = new long[4 * N];
		dp = new long[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			ary[i] = Long.parseLong(st.nextToken());
		}
		long maxAnswer = Long.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			int l = Math.max(0, i - D);
			int r = i - 1;
			dp[i] = ary[i] + getMax(1, N, 1, l, r);
			maxAnswer = Math.max(dp[i], maxAnswer);
			update(1, N, 1, i, dp[i]);
		}
//		for(int i=1;i<=N;i++) {
//			System.out.print(dp[i] + " " );
//		}System.out.println();
		System.out.println(maxAnswer);
		
	}

	private static void update(int s, int e, int idx, int c, long val) {
		if (c < s || e < c)
			return;
		if (s == e) {
			seg[idx] = val;
			return;
		}
		int mid = (s + e) / 2;
		update(s, mid, idx * 2, c, val);
		update(mid + 1, e, idx * 2 + 1, c, val);
		seg[idx] = Math.max(seg[idx * 2], seg[idx * 2 + 1]);
	}

	private static long getMax(int s, int e, int idx, int l, int r) {
		if (r < s || e < l)
			return 0;
		if (l <= s && e <= r)
			return seg[idx];
		int mid = (s + e) / 2;
		return Math.max(getMax(s, mid, idx * 2, l, r), getMax(mid + 1, e, idx * 2 + 1, l, r));
	}

}