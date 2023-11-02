import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int ary[];
	static int seg[];
	static long answer = 0;
	static int N;

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		ary = new int[N + 1];
		ary[0] = Integer.MAX_VALUE;
		seg = new int[4 * N];
		for (int i = 1; i <= N; i++) {
			ary[i] = Integer.parseInt(br.readLine());
		}
		init(1, N, 1);
		getArea(1, N);
		System.out.println(answer);

	}

	private static void getArea(int s, int e) {
		if (s > e)
			return;
		int min = getMin(1, N, 1, s, e);
		long area = (long)ary[min] * (long)(e - s + 1);
		answer = Math.max(area, answer);
		getArea(s, min - 1);
		getArea(min + 1, e);

	}

	private static int getMin(int s, int e, int idx, int l, int r) {
		if (r < s || e < l)
			return 0;
		if (l <= s && e <= r)
			return seg[idx];
		int mid = (s + e) >> 1;
		int left = getMin(s, mid, idx << 1, l, r);
		int right = getMin(mid + 1, e, idx << 1 | 1, l, r);
		return ary[left] <= ary[right] ? left : right;
	}

	private static int init(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = s;
		int mid = (s + e) >> 1;
		int left = init(s, mid, idx << 1);
		int right = init(mid + 1, e, idx << 1 | 1);
		return seg[idx] = ary[left] <= ary[right] ? left : right;

	}
}