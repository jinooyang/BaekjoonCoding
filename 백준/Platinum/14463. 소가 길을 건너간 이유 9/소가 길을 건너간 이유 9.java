import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int ary[];
	static int seg[];

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		n = 2*n;
		ary = new int[n];
		seg = new int[4 * n];
		for (int i = 0; i < n; i++) {
			ary[i] = Integer.parseInt(br.readLine());
		}
		Map<Integer, Integer> map = new HashMap<>();
		int answer = 0;
		for (int i = 0; i < n; i++) {
			if (!map.containsKey(ary[i])) {
				map.put(ary[i], i);
				update(0, n - 1, 1, i, 1);
			} else {
				int x = getSum(0, n - 1, 1, map.get(ary[i]) + 1, i - 1);
				update(0, n - 1, 1, map.get(ary[i]), -1);
				map.remove(ary[i]);
				answer += x;
			}

		}
		System.out.println(answer);
	}

	private static int getSum(int s, int e, int idx, int l, int r) {
		if (r < s || e < l)
			return 0;
		if (l <= s && e <= r)
			return seg[idx];
		int mid = (s + e) >> 1;
		return getSum(s, mid, idx * 2, l, r) + getSum(mid + 1, e, idx * 2 + 1, l, r);
	}

	private static void update(int s, int e, int idx, int c, int val) {
		if (c < s || e < c)
			return;

		seg[idx] += val;
		if (s == e)
			return;

		int mid = (s + e) >> 1;
		update(s, mid, idx * 2, c, val);
		update(mid + 1, e, idx * 2 + 1, c, val);
	}
}