import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int MOD = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		//백만까지 구해놓고 답을 출력하자
		int max = 1_000_001;
		long[][] dp = new long[max][2];
		//다이나믹 프로그래밍
		dp[1][0] = 2;
		dp[1][1] = 7;
		dp[2][0] = 10;
		dp[2][1] = 33;
		for (int i = 3; i < max; i++) {
			dp[i][0] = ((dp[i - 1][0] * 2) % MOD+dp[i-1][1])%MOD - dp[i - 2][1] % MOD;
			if (dp[i][0] < 0)
				dp[i][0] += MOD;
			dp[i][1] = (dp[i][0] * 4) % MOD - (dp[i - 1][1]) % MOD;
			if (dp[i][1] < 0)
				dp[i][1] += MOD;
		}
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			int x = Integer.parseInt(br.readLine());
			sb.append(dp[x][1]).append("\n");

		}
		System.out.println(sb);

	}

}