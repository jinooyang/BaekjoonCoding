import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int ary[][] = new int[n][3];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			ary[i][0] = Integer.parseInt(st.nextToken());// r
			ary[i][1] = Integer.parseInt(st.nextToken());// g
			ary[i][2] = Integer.parseInt(st.nextToken());// b

		}

		int dp[][] = new int[n][3];

		int minAnswer = Integer.MAX_VALUE;
		for (int startRGB = 0; startRGB < 3; startRGB++) {
			dp[0][startRGB] = ary[0][startRGB];
			dp[0][(startRGB + 1) % 3] = Integer.MAX_VALUE;
			dp[0][(startRGB + 2) % 3] = Integer.MAX_VALUE;

			dp[1][startRGB] = Integer.MAX_VALUE;
			dp[1][(startRGB + 1) % 3] = Math.min(dp[0][startRGB], dp[0][(startRGB + 1) % 3])
					+ ary[1][(startRGB + 1) % 3];
			dp[1][(startRGB + 2) % 3] = Math.min(dp[0][startRGB], dp[0][(startRGB + 2) % 3])
					+ ary[1][(startRGB + 2) % 3];

			for (int i = 2; i < n; i++) {
				dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + ary[i][0];
				dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + ary[i][1];
				dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + ary[i][2];

			}

//			for (int i = 0; i < 3; i++) {
//				for (int j = 0; j < n; j++) {
//					System.out.print(dp[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("----------------------------------------------");

			for (int i = 0; i < 3; i++) {
				if (i != startRGB) {
					minAnswer = Math.min(minAnswer, dp[n - 1][i]);
				}
			}
		}

		System.out.println(minAnswer);

	}
}