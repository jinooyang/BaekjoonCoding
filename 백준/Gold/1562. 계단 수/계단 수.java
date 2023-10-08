import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int answer = 0;
	static int n;
	static int dp[][][] = new int[10][1024][100];
	static int all = 1023;

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 1024; j++) {
				for (int k = 0; k < 100; k++) {
					dp[i][j][k] = -1;
				}
			}
		}
		for (int i = 1; i <= 9; i++) {
			answer += dfs(1, i, 1 << i);
			answer = answer % 1_000_000_000;
		}
		System.out.println(answer);
	}

	private static int dfs(int cnt, int before, int history) {
		if (cnt == n) {
			if (history == 1023)
				return 1;
			else
				return 0;
		}

		int plus = 0;
		int minus = 0;

		int next_plus = before + 1;
		int next_minus = before - 1;

		int remaining = n - cnt;

		if (next_plus <= 9) {
			int next_history = history | (1 << next_plus);
			if (dp[next_plus][next_history][remaining] == -1) {
				dp[next_plus][next_history][remaining] = dfs(cnt + 1, next_plus, next_history) % 1_000_000_000;
			}
			plus = dp[next_plus][next_history][remaining];
		}
		if (next_minus >= 0) {
			int next_history = history | (1 << next_minus);
			if (dp[next_minus][next_history][remaining] == -1) {
				dp[next_minus][next_history][remaining] = dfs(cnt + 1, next_minus, next_history) % 1_000_000_000;
			}
			minus = dp[next_minus][next_history][remaining];
		}

		return (plus + minus) % 1_000_000_000;
	}

}