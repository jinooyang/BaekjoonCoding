import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int ary[] = new int[n];
		for (int i = 0; i < n; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}
		int dp[] = new int[n];
		int maxAnswer = 0;
		for (int k = 0; k < n; k++) {
			dp[k] = 1;
			for (int i = 0; i < k; i++) {
				if (ary[i] < ary[k]) {
					dp[k] = Math.max(dp[k], dp[i] + 1);
				}
			}

			maxAnswer = Math.max(dp[k], maxAnswer);
		}
		System.out.println(maxAnswer);
	}
}