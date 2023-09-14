import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int seg[];
	static int ary[];
	static int lazy[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		ary = new int[n];
		lazy = new int[4 * n];
		seg = new int[4 * n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			ary[i] = Integer.parseInt(st.nextToken());
		init(0, n - 1, 1);
		st = new StringTokenizer(br.readLine());
		int Q = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int flag = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			if (flag == 1) {
				int k = Integer.parseInt(st.nextToken());
				// i , j 구간에 k를 xor한다
				update(0, n - 1, 1, i, j, k);
			}
			if (flag == 2) {
				// i, j구간을 모두 xor해서 출력한다
				sb.append(getXor(0, n - 1, 1, i, j)).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static void updateLazy(int s, int e, int idx) {
		if (lazy[idx] != 0) {
			seg[idx] ^= lazy[idx] * ((e - s + 1) % 2);
			if (s != e) {
				lazy[idx * 2] ^= lazy[idx];
				lazy[idx * 2 + 1] ^= lazy[idx];
			}
			lazy[idx] = 0;
		}
	}

	private static int getXor(int s, int e, int idx, int l, int r) {
		updateLazy(s, e, idx);
		if (r < s || e < l)
			return 0;

		if (l <= s && e <= r)
			return seg[idx];

		int mid = (s + e) / 2;
		return getXor(s, mid, idx * 2, l, r) ^ getXor(mid + 1, e, idx * 2 + 1, l, r);
	}

	private static void update(int s, int e, int idx, int l, int r, int k) {
		updateLazy(s, e, idx);
		if (r < s || e < l)
			return;
		if (l <= s && e <= r) {
			seg[idx] ^= k * ((e - s + 1) % 2);
			if (s != e) {
				lazy[idx * 2] ^= k;
				lazy[idx * 2 + 1] ^= k;
			}
			return;
		}
		int mid = (s + e) / 2;
		update(s, mid, idx * 2, l, r, k);
		update(mid + 1, e, idx * 2 + 1, l, r, k);
		seg[idx] = seg[idx * 2] ^ seg[idx * 2 + 1];

	}

	private static int init(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = ary[s];
		int mid = (s + e) / 2;
		return seg[idx] = init(s, mid, idx * 2) ^ init(mid + 1, e, idx * 2 + 1);
	}
}