import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int ary[][] = new int[n][n];
			int mid = n / 2;
			int sum = 0;
			for (int i = 0; i < n / 2; i++) {
				st = new StringTokenizer(br.readLine());
				String s = st.nextToken();
				for (int j = 0; j < mid; j++) {
					ary[i][j] = s.charAt(j) - '0';
				}
				for (int j = mid; j < n - mid; j++) {
					ary[i][j] = s.charAt(j) - '0';
					sum += ary[i][j];
				//	System.out.println("A더하기 : " + i + " " + j);

				}
				for (int j = n - mid; j < n; j++) {
					ary[i][j] = s.charAt(j) - '0';

				}
				mid--;
			}
			for (int i = n / 2; i < n - (n / 2); i++) {
				st = new StringTokenizer(br.readLine());
				String s = st.nextToken();
				for (int j = 0; j < mid; j++) {
					ary[i][j] = s.charAt(j) - '0';
				}
				for (int j = mid; j < n - mid; j++) {
					ary[i][j] = s.charAt(j) - '0';
					sum += ary[i][j];
					//System.out.println("B더하기 : " + i + " " + j);

				}
				for (int j = n - mid; j < n; j++) {
					ary[i][j] = s.charAt(j) - '0';

				}
				mid++;
			}
			for (int i = n - (n / 2); i < n; i++) {
				//System.out.println(mid);
				st = new StringTokenizer(br.readLine());
				String s = st.nextToken();
				for (int j = 0; j < mid; j++) {
					ary[i][j] = s.charAt(j) - '0';
				}
				for (int j = mid; j < n - mid; j++) {
					ary[i][j] = s.charAt(j) - '0';
					sum += ary[i][j];
					//System.out.println("C더하기 : " + i + " " + j);


				}
				for (int j = n - mid; j < n; j++) {
					ary[i][j] = s.charAt(j) - '0';

				}
				mid++;
			}
//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j < n; j++) {
//					System.out.print(ary[i][j] + " ");
//				}
//				System.out.println();
//			}
			System.out.println("#" + test_case + " " +  sum);

		}
	}
}