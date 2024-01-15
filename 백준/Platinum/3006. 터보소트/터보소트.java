import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int seg[];
	static int index[];

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		index = new int[N + 1];
		seg = new int[4 * N];
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			int x = Integer.parseInt(br.readLine());
			index[x] = i;// 인덱스를 저장한다
		}

		init(1, N, 1);

		int front = 1;
		int end = N;
		int cnt = 1;
		while (true) {

			if (N % 2 == 0) {
				if (front > end)
					break;
			}
			if (N % 2 != 0) {// 홀수의 경우
				if (front > end)
					break;
			}

			int x = 0;
			int l = 1;
			int r = N;
			int c = 0;
			if (cnt % 2 == 1) {
				x = front;
				r = index[front];
				c = r;
				front++;
			} else {
				x = end;
				l = index[end];
				c = l;
				end--;
			}
//			System.out.println("x : " + x);
			int ans = getSum(1, N, 1, l, r) - 1;
			update(1, N, 1, c);
			sb.append(ans).append("\n");
			cnt++;
		}
		System.out.println(sb);
		
	}

	private static int init(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = 1;
		int mid = (s + e) >> 1;
		return seg[idx] = init(s, mid, idx * 2) + init(mid + 1, e, idx * 2 + 1);

	}

	private static int getSum(int s, int e, int idx, int l, int r) {
		if (r < s || e < l)
			return 0;
		if (l <= s && e <= r)
			return seg[idx];
		int mid = (s + e) >> 1;
		return getSum(s, mid, idx * 2, l, r) + getSum(mid + 1, e, idx * 2 + 1, l, r);
	}

	private static void update(int s, int e, int idx, int c) {
		if (c < s || e < c)
			return;
		if (s == e) {
			seg[idx] = 0;
			return;
		}
		int mid = (s + e) >> 1;
		update(s, mid, idx * 2, c);
		update(mid + 1, e, idx * 2 + 1, c);
		seg[idx] = seg[idx * 2] + seg[idx * 2 + 1];
	}
}