import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int ary[];
	static int seg[];
	static long maxAnswer;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		ary = new int[n + 1];
		seg = new int[4 * n];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}
		ary[0] = Integer.MAX_VALUE;
		init(1, n, 1);
		maxAnswer = 0;
		getAnswer(1, n);
		System.out.println(maxAnswer);
	}

	private static void getAnswer(int l, int r) {
		if (l > r)
			return;
		int x = getMin(1, n, 1, l, r);
		int est = (r - l + 1) * ary[x];
//		System.out.println(l+" " + r + " "+x + " " + est);
		maxAnswer = Math.max(maxAnswer, est); 
		getAnswer(l, x - 1);
		getAnswer(x + 1, r);
	}

	private static int getMin(int s, int e, int idx, int l, int r) {
		if (r < s || e < l)
			return 0;
		if (l <= s && e <= r)
			return seg[idx];
		int mid = (s + e) >> 1;
		int left = getMin(s, mid, idx * 2, l, r);
		int right = getMin(mid + 1, e, idx * 2 + 1, l, r);
		if (ary[left] <= ary[right])
			return left;
		return right;
	}

	private static int init(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = s;
		int mid = (s + e) >> 1;
		int l = init(s, mid, idx * 2);
		int r = init(mid + 1, e, idx * 2 + 1);
		if (ary[l] <= ary[r])
			return seg[idx] = l;
		return seg[idx] = r;
	}
}