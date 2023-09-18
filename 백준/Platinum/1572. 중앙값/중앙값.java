import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int MAX = 65535;
	static long seg[] = new long[4 * MAX];

	static int index = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		long answer = 0;
		int ary[] = new int[n];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			ary[i] = x;
			update(0, MAX, 1, x, 1);
			if (i == k - 1) {
				query(0, MAX, 1, (k + 1) / 2);
				answer += index;
			}
			// update seg
		}
		for (int i = k; i < n; i++) {
			index = -1;
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			ary[i] = x;
			update(0, MAX, 1, ary[i - k], -1);
			update(0, MAX, 1, ary[i], 1);
			query(0, MAX, 1, (k + 1) / 2);
			answer += index;

		}
		System.out.println(answer);
	}

	public static long query(int s, int e, int idx, long k) {
		if (s == e) {
			index = s;
			return seg[idx];
		}
		long l = seg[idx * 2];
		int mid = (s + e) / 2;
		if (l >= k) {
			return query(s, mid, idx * 2, k);
		}
		return query(mid + 1, e, idx * 2 + 1, k - l);
	}

	public static void update(int s, int e, int idx, int x, int val) {
		if (x < s || e < x)
			return;

		seg[idx] += val;
		if (s == e)
			return;
		int mid = (s + e) / 2;
		update(s, mid, idx * 2, x, val);
		update(mid + 1, e, idx * 2 + 1, x, val);

	}

}