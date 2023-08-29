import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int dpVal;
		int originalVal;

		public Node(int dpVal, int originalVal) {
			super();
			this.dpVal = dpVal;
			this.originalVal = originalVal;
		}

		@Override
		public String toString() {
			return "Node [dpVal=" + dpVal + ", originalVal=" + originalVal + "]";
		}

	}

	static Node seg[];
	static int ary[];
	static Map<Integer, Integer> map;
	static Map<Integer, Integer> map2;

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
		seg = new Node[4 * n];
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
		int maxNow = 0;
		Map<Integer, Integer> dpMap = new HashMap<>();
		Map<Integer, List<Integer>> dpList = new HashMap<>();
		for (int k = 0; k < N; k++) {
			int now = originAry[k];
			// now의 DP갱신
			// 세그트리에서 현재 값보다 작은 범위에서 가장 큰 값을 구하자
			Node dp = findMax(0, n - 1, 1, 0, map.get(now) - 1);
			// System.out.println(now + " " + dp);
			// dp.dpVal += 1;
			int dpx = dp.dpVal + 1;
			// System.out.println(dp);
			// 같은 수 일리가 없다
			if (dpMap.containsKey(now)) {
				if (dpMap.get(now) < dpx) {
					dpMap.put(now, dpx);
					// dpMap.put(최대를 반환한 노드, value)

					dpList.get(now).clear();
					// System.out.println(dp);
					for (int i = 0; i < dpList.get(dp.originalVal).size(); i++) {
						dpList.get(now).add(dpList.get(dp.originalVal).get(i));
					}
					dpList.get(now).add(now);

					update(0, n - 1, 1, map.get(now), dpx);
				}
			} else {// 처음 보는 값인 경우
				dpMap.put(now, dpx);
				dpList.put(now, new ArrayList<>());
				if (dpList.containsKey(dp.originalVal))
					for (int i = 0; i < dpList.get(dp.originalVal).size(); i++) {
						dpList.get(now).add(dpList.get(dp.originalVal).get(i));
					}
				dpList.get(now).add(now);
				update(0, n - 1, 1, map.get(now), dpx);
			}

			// System.out.println(dpList.get(now));
//			if (dpAry[now] < dp) {
//				dpAry[now] = dp;
//				update(0, n - 1, 1, map.get(now), dp);
//			}
			// update(0, n - 1, 1, map.get(now), dp);// 큰 값으로 갱신 된 경우에만 업데이트해야한다

			// maxAnswer = Math.max(maxAnswer, dp.dpVal);
			if (dpx > maxAnswer) {
				// System.out.println(now);
				maxAnswer = dpx;
				maxNow = now;
			}
		}

//		for (Integer i : dpList.keySet()) {
//			System.out.println(i + "!! : " + dpList.get(i).toString());
//		}
		// 정답
		System.out.println(dpList.get(maxNow).size());
		for (int i = 0; i < dpList.get(maxNow).size(); i++) {
			System.out.print(dpList.get(maxNow).get(i) + " ");
		}

	}

	private static Node findMax(int s, int e, int idx, int l, int r) {
		if (r < s || e < l) {
			return new Node(0, 0);
		}
		if (l <= s && e <= r) {
			return seg[idx];
		}
		int mid = (s + e) / 2;
		Node left = findMax(s, mid, idx * 2, l, r);
		Node right = findMax(mid + 1, e, idx * 2 + 1, l, r);
		return left.dpVal > right.dpVal ? left : right;

		// return Math.max(findMax(s, mid, idx * 2, l, r), findMax(mid + 1, e, idx * 2 +
		// 1, l, r));
	}

	private static Node update(int s, int e, int idx, int chg, int val) {
		if (chg < s || chg > e)
			return seg[idx];
		if (s == e) {
			seg[idx].dpVal = val;
			return seg[idx];
		}
		int mid = (s + e) / 2;
		Node left = update(s, mid, idx * 2, chg, val);
		Node right = update(mid + 1, e, idx * 2 + 1, chg, val);
		if (left.dpVal > right.dpVal) {
			seg[idx].dpVal = left.dpVal;
			seg[idx].originalVal = left.originalVal;
			return seg[idx];
		}

		seg[idx].dpVal = right.dpVal;
		seg[idx].originalVal = right.originalVal;
		return seg[idx];
	}

	private static Node init(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = new Node(ary[s], map2.get(s));
		int mid = (s + e) / 2;
		Node left = init(s, mid, idx * 2);
		Node right = init(mid + 1, e, idx * 2 + 1);
		return seg[idx] = left.dpVal > right.dpVal ? new Node(left.dpVal, left.originalVal)
				: new Node(right.dpVal, right.originalVal);

	}

}