import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 구간합 세그먼트트리
	private static SegNode seg[];

	private static long ary[];
	// 구갑한 LAZY SEG
	private static LazyNode lazy[];

	private static class SegNode {
		long sum;
		long length;

		public SegNode(long sum, long length) {
			this.sum = sum;
			this.length = length;
		}

		@Override
		public String toString() {
			return "SegNode [sum=" + sum + ", length=" + length + ", getClass()=" + getClass() + ", hashCode()="
					+ hashCode() + ", toString()=" + super.toString() + "]";
		}

	}

	private static class LazyNode {
		long startSum;
		long cnt;

		public LazyNode(long sum, long cnt) {
			super();
			this.startSum = sum;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "LazyNode [sum=" + startSum + ", cnt=" + cnt + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		ary = new long[N + 1];
		seg = new SegNode[4 * N];
		lazy = new LazyNode[4 * N];
		for (int i = 0; i < 4 * N; i++) {
			seg[i] = new SegNode(0, 0);
			lazy[i] = new LazyNode(0, 0);
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}
		init(1, N, 1);
//		pringSeg(N);
		st = new StringTokenizer(br.readLine());
		int Q = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int flag = Integer.parseInt(st.nextToken());
			if (flag == 1) {
				// 별똥별이 떨어진다
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				update(1, N, 1, l, r);
//				System.out.println("update : " + l + " "+r) ;
//				pringSeg(N);
//				pringLazy(N);
//				System.out.println();

			} else {
				// 별의 개수 구하기
				int idx = Integer.parseInt(st.nextToken());
				long ans = getSum(1, N, 1, idx, idx);
				sb.append(ans).append("\n");
//				System.out.println("getSum : " + idx );
//				pringSeg(N);
//				pringLazy(N);
//				System.out.println();
			}
		}
		System.out.println(sb);

	}

	private static void pringSeg(int N) {
		System.out.print("seg : ");
		for (int i = 0; i < 4 * N; i++) {
			System.out.print(seg[i].sum + " ");
		}
		System.out.println();
	}

	private static void pringLazy(int N) {
		for (int i = 0; i < 4 * N; i++) {
			System.out.print(lazy[i].startSum + " ");
		}
		System.out.println();
	}

	private static void updateLazy(int s, int e, int idx) {
		if (lazy[idx].cnt > 0) {
//			if(s==e && e==7) {
//				System.out.println("여기서 7이 업데이ㅡㅌ 됩ㄴ디ㅏ");
//			}
			long val = calculateSum(lazy[idx].startSum, seg[idx].length, lazy[idx].cnt);
//			if(s==e && e==7) {
//				System.out.println(lazy[idx].startSum + " " + lazy[idx].cnt);
//			}
			seg[idx].sum += val;

			if (s != e) {
				lazy[idx * 2].cnt+=lazy[idx].cnt;
				lazy[idx * 2].startSum += lazy[idx].startSum;

				lazy[idx * 2 + 1].cnt+=lazy[idx].cnt;
				lazy[idx * 2 + 1].startSum += lazy[idx].startSum + seg[idx * 2].length * lazy[idx].cnt;

			}
			lazy[idx].cnt = 0;
			lazy[idx].startSum = 0;
		}
	}

	private static void update(int s, int e, int idx, int l, int r) {
		updateLazy(s, e, idx);
		if (r < s || e < l) {
			return;
		}
		if (l <= s && e <= r) {
			long val = calculateSum(s - l + 1, e - s + 1, 1);
			seg[idx].sum += val;
			if (s != e) {
				lazy[idx * 2].cnt++;
				// 합, 카운트, 공차
				lazy[idx * 2].startSum += s - l + 1;

				lazy[idx * 2 + 1].cnt++;
				lazy[idx * 2 + 1].startSum += s - l + 1 + seg[idx * 2].length;

			}
			return;
		}
		int mid = (s + e) >> 1;
		update(s, mid, idx * 2, l, r);
		update(mid + 1, e, idx * 2 + 1, l, r);
		seg[idx].sum = seg[idx * 2].sum + seg[idx * 2 + 1].sum;
	}

	private static long getSum(int s, int e, int idx, int l, int r) {
		updateLazy(s, e, idx);
		if (r < s || e < l) {
			return 0;
		}
		if (l <= s && e <= r) {
			return seg[idx].sum;
		}
		int mid = (s + e) >> 1;
		return getSum(s, mid, idx * 2, l, r) + getSum(mid + 1, e, idx * 2 + 1, l, r);
	}

	private static SegNode init(int s, int e, int idx) {
		if (s == e) {
			seg[idx].sum = ary[s];
			seg[idx].length = 1;
			return seg[idx];
		}
		int mid = (s + e) / 2;
		SegNode left = init(s, mid, idx * 2);
		SegNode right = init(mid + 1, e, idx * 2 + 1);
		seg[idx].length = left.length + right.length;
		seg[idx].sum = left.sum + right.sum;
		return seg[idx];
	}

	private static long calculateSum(long a, long n, long d) {
		return (n * (2 * a + (n - 1) * d)) / 2;
	}

}