import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int ary[];
	static int seg[];
	static int N;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		ary = new int[N + 1];
		ary[0] = Integer.MAX_VALUE;
		seg = new int[4 * N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			ary[i + 1] = Integer.parseInt(st.nextToken());
		}
		init(1, N, 1);
		getMaxArea(1,N);
		System.out.println(answer);
	}

	private static void getMaxArea(int s, int e) {
		if (s > e)
			return;
		int min = getMin(1, N, 1, s, e);
		int area = (e - s + 1) * ary[min];
		answer = Math.max(area, answer);
		getMaxArea(s, min - 1);
		getMaxArea(min + 1, e);
	}

	private static int getMin(int s, int e, int idx, int l, int r) {
		if (r < s || e < l)
			return 0;
		if (l <= s && e <= r)
			return seg[idx];
		int mid = (s + e) / 2;
		int left = getMin(s, mid, idx * 2, l, r);
		int right = getMin(mid + 1, e, idx * 2 + 1, l, r);
		return ary[left] <= ary[right] ? left : right;
	}

	private static int init(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = s;
		int mid = (s + e) / 2;
		int left = init(s, mid, idx * 2);
		int right = init(mid + 1, e, idx * 2 + 1);
		return seg[idx] = ary[left] <= ary[right] ? left : right;
	}
}
