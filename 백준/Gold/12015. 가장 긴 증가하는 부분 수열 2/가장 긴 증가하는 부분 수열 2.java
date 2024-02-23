import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int x;
		int idx;

		public Node(int x, int idx) {
			super();
			this.x = x;
			this.idx = idx;
		}

		@Override
		public int compareTo(Node o) {
			if (this.x != o.x) {
				return Integer.compare(this.x, o.x);
			} else
				return Integer.compare(this.idx, o.idx);
		}

	}

	static int seg[];
	static int ary[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int originAry[] = new int[N];// 원래 인풋 그대로 저장
		Set<Integer> set = new HashSet<>(); // 중복 제거를 위한 셋
		for (int i = 0; i < N; i++) {
			originAry[i] = Integer.parseInt(st.nextToken());
			set.add(originAry[i]);
		}
		int n = set.size();
		// System.out.println(set);
		ary = new int[n];
		seg = new int[4 * n];
		int idx = 0;
		for (Integer i : set) {
			ary[idx++] = i;

		}

		Arrays.sort(ary);
		// ----------------------------------------------
		Map<Integer, Integer> map = new HashMap<>(); // 각 수의 인덱스를 저장하자
		for (int i = 0; i < n; i++) {
			map.put(ary[i], i);
		}
		// System.out.println(map);
		Arrays.fill(ary, 0);
		init(0, n - 1, 1);

		int maxAnswer = 0;
		int dpAry[] = new int[1000001];
		for (int k = 0; k < N; k++) {
			int now = originAry[k];
			// now의 DP갱신
			// 세그트리에서 현재 값보다 작은 범위에서 가장 큰 값을 구하자
			int dp = findMax(0, n - 1, 1, 0, map.get(now) - 1);
			dp += 1;
			// System.out.println(dp);
			// 같은 수 일리가 없다
			if(dpAry[now] < dp) {
				dpAry[now] = dp;
				update(0, n - 1, 1, map.get(now), dp);
			}
			//update(0, n - 1, 1, map.get(now), dp);// 큰 값으로 갱신 된 경우에만 업데이트해야한다
			
			maxAnswer = Math.max(maxAnswer, dp);
		}
		System.out.println(maxAnswer);
	}

	private static int findMax(int s, int e, int idx, int l, int r) {
		if (r < s || e < l) {
			return 0;
		}
		if (l <= s && e <= r) {
			return seg[idx];
		}
		int mid = (s + e) / 2;
		return Math.max(findMax(s, mid, idx * 2, l, r), findMax(mid + 1, e, idx * 2 + 1, l, r));
	}

	private static int update(int s, int e, int idx, int chg, int val) {
		if (chg < s || chg > e)
			return seg[idx];
		if (s == e) {
			return seg[idx] = val;
		}
		int mid = (s + e) / 2;

		return seg[idx] = Math.max(update(s, mid, idx * 2, chg, val), update(mid + 1, e, idx * 2 + 1, chg, val));
	}

	private static int init(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = ary[s];
		int mid = (s + e) / 2;
		return seg[idx] = Math.max(init(s, mid, idx * 2), init(mid + 1, e, idx * 2 + 1));

	}

}