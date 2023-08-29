import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long dp[] = new long[101];
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;
		for (int i = 4; i < 101; i++) {
			dp[i] = dp[i - 2] + dp[i - 3];
		}
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			System.out.println(dp[x]);
		}
	}
}