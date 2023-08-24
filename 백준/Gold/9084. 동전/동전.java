import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < t; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int coins[] = new int[n];
			for (int i = 0; i < n; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			int price = Integer.parseInt(st.nextToken());
			int dp[][] = new int[n][price + 1];

			for (int i = 0; i < n; i++) {
				dp[i][0] = 1;
				for (int j = 1; j < price + 1; j++) {
					if (j >= coins[i])
						dp[i][j] = dp[i][j - coins[i]];
					if (i >= 1) {
						dp[i][j] += dp[i - 1][j];
					}
				}
			}
//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j <= price; j++) {
//					System.out.print(dp[i][j] + " ");
//				}
//				System.out.println();
//			}
			System.out.println(dp[n - 1][price]);
		}

	}
}