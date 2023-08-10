import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Solution {
	static int n;
	static int total;
	static int minDiff;
	static int ary[][];
	static Set<Integer> set;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		// int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			// 입력 & 초기화
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			ary = new int[n][n];
			total = 0;
			minDiff = Integer.MAX_VALUE;
			set = new HashSet<>();
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					ary[i][j] = Integer.parseInt(st.nextToken());
					total += ary[i][j];
				}
			}
			// 순열 & 계산

			comb(0, 0);
			System.out.println("#" + test_case + " " + minDiff);
		}
	}

	private static void comb(int cnt, int beforeused) {
		if (cnt == n / 2) {
			int A = 0;
			int B = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i == j)
						continue;
					else if (set.contains(i) && set.contains(j)) {
						A += ary[i][j];

					} else if (!set.contains(i) && !set.contains(j)) {

						B += ary[i][j];
					} else
						continue;
				}
			}
			int diff = Math.abs(A - B);
			if (minDiff > diff)
				minDiff = diff;
			return;
		}
		for (int i = beforeused + 1; i < n; i++) {
			set.add(i);
			comb(cnt + 1, i);
			set.remove(i);
		}
	}
}