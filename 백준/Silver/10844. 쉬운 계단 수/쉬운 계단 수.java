import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static long answer = 0;
	static int n;
	static long dp[][] = new long[10][100];

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		for (int i = 0; i < 10; i++) {
			Arrays.fill(dp[i], -1);
		}
		for (int i = 1; i <= 9; i++) {
			answer += dfs(1, i);
			answer=answer%1_000_000_000;
		}
		System.out.println(answer);
	}

	private static long dfs(int cnt, int before) {
		if (cnt == n)
			return 1;
		long plus = 0;
		long minus = 0;
		int remaining = n - cnt;
		if (before + 1 >= 0 && before + 1 <= 9) {
			if (dp[before + 1][remaining] != -1) {
				plus = dp[before + 1][remaining];
			} else {
				dp[before + 1][remaining] = dfs(cnt + 1, before + 1);
				plus = dp[before + 1][remaining];
			}
		}
		if (before - 1 >= 0 && before - 1 <= 9) {
			if (dp[before - 1][remaining] != -1) {
				minus = dp[before - 1][remaining];
			} else {
				dp[before - 1][remaining] = dfs(cnt + 1, before - 1);
				minus = dp[before - 1][remaining];
			}
		}
		return (plus%1_000_000_000 + minus%1_000_000_000)%1_000_000_000;
	}

}