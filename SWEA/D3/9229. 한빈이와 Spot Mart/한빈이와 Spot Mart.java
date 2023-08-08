import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		// StringTokenizer st = null;
		// int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			// 입력
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());// number of chips
			int m = Integer.parseInt(st.nextToken());// maxweight
			int ary[] = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				ary[i] = Integer.parseInt(st.nextToken());
			}
			// 계산
			int answer = -1;
			// 두개를 선택했을 경우 최대
			for (int i = 0; i < n - 1; i++) {
				for (int j = i+1; j < n; j++) {
					int sum = ary[i] + ary[j];
					if (sum <= m && sum > answer)
						answer = sum;
				}
			}
			// 출력
			System.out.println("#" + test_case + " " + answer);
		}
	}
}