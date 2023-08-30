import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		if (n == 1) {
			System.out.println(1);
			return;
		}
		int dp[] = new int[n + 1];
		dp[1] = 1;
		dp[2] = 3;
		for (int i = 3; i < n + 1; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 2]) % 10007;
		}
		System.out.println(dp[n]);
	}
}