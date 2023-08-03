import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static int ary[][];
	static int n;
	static int m;

	static int findSum(int i, int j) {
		int sum = 0;
		for (int y = i; y < i + m; y++) {
			for (int x = j; x < j + m; x++) {
				sum += ary[y][x];
			}
		}
		return sum;
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			ary = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					ary[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int answer = 0;
			for (int i = 0; i < n - m + 1; i++) {
				for (int j = 0; j < n - m + 1; j++) {
					int flyCount = findSum(i, j);
					if (flyCount > answer)
						answer = flyCount;
				}
			}
			System.out.println("#" + test_case + " " + answer);

		}
	}
}