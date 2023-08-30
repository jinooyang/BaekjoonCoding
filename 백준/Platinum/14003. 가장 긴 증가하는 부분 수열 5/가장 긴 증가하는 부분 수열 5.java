import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int seg[];
	static int ary[];
	static Map<Integer, Integer> map;
	static Map<Integer, Integer> map2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int originAry[] = new int[N];// 원래 인풋 그대로 저장
		int dpAry[] = new int[N];
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
		map = new HashMap<>(); // 각 수의 인덱스를 저장하자
		map2 = new HashMap<>(); // 각 수의 인덱스를 저장하자
		for (int i = 0; i < n; i++) {
			map.put(ary[i], i);
			map2.put(i, ary[i]);
		}
		// System.out.println(map);
		Arrays.fill(ary, 0);
		// System.out.println(map);
		init(0, n - 1, 1);

		int maxAnswer = 0;
		Map<Integer, Integer> dpMap = new HashMap<>();
		for (int k = 0; k < N; k++) {
			int now = originAry[k];
			// now의 DP갱신
			// 세그트리에서 현재 값보다 작은 범위에서 가장 큰 값을 구하자
			int dp = findMax(0, n - 1, 1, 0, map.get(now) - 1) + 1;

			// 같은 수 일리가 없다
			if (dpMap.containsKey(now)) {
				if (dpMap.get(now) < dp) {
					dpMap.put(now, dp);
					update(0, n - 1, 1, map.get(now), dp);
				}
			} else {// 처음 보는 값인 경우
				dpMap.put(now, dp);
				update(0, n - 1, 1, map.get(now), dp);
			}

			if (dp > maxAnswer)
				maxAnswer = dp;

			dpAry[k] = dp;
		}

		// 정답
		System.out.println(maxAnswer);
		int cnt = maxAnswer;
		StringBuilder sb = new StringBuilder();
		Stack<Integer> answer = new Stack<>();
		for (int i = N - 1; i >= 0; i--) {
			if (dpAry[i] == cnt) {
				answer.add(originAry[i]);
				// sb.append(originAry[i]).append(" ");
				cnt--;
			}
		}
		while (!answer.isEmpty()) {
			sb.append(answer.pop()).append(" ");
		}
		System.out.println(sb);

	}

	private static int findMax(int s, int e, int idx, int l, int r) {
		if (r < s || e < l) {
			return 0;
		}
		if (l <= s && e <= r) {
			return seg[idx];
		}
		int mid = (s + e) / 2;
		int left = findMax(s, mid, idx * 2, l, r);
		int right = findMax(mid + 1, e, idx * 2 + 1, l, r);
		return left > right ? left : right;

	}

	private static int update(int s, int e, int idx, int chg, int val) {
		if (chg < s || chg > e)
			return seg[idx];
		if (s == e) {
			seg[idx] = val;
			return seg[idx];
		}
		int mid = (s + e) / 2;
		int left = update(s, mid, idx * 2, chg, val);
		int right = update(mid + 1, e, idx * 2 + 1, chg, val);
		if (left > right) {
			seg[idx] = left;

			return seg[idx];
		}

		seg[idx] = right;

		return seg[idx];
	}

	private static int init(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = ary[s];
		int mid = (s + e) / 2;
		int left = init(s, mid, idx * 2);
		int right = init(mid + 1, e, idx * 2 + 1);
		return seg[idx] = left > right ? left : right;

	}

}