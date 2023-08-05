import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int ary[] = new int[n + 3];
		for (int i = 3; i < n + 3; i++) {
			st = new StringTokenizer(br.readLine());
			ary[i] = Integer.parseInt(st.nextToken());
		}
		int dp[][] = new int[n + 3][2];
		for (int i = 3; i < n + 3; i++) {
			int a = 0;// dp i-3 + aryi-2 + aryi
			int b = 0;// dp i-3 + aryi-1 + aryi
			int c = 0;// dp i-2 + aryi
			if (!(dp[i - 3][1] == 2)) {
				a = dp[i - 3][0] + ary[i - 2] + ary[i];
			}
			b = dp[i - 3][0] + ary[i - 1] + ary[i];
			c = dp[i - 2][0] + ary[i];
			if (a >= b) {
				dp[i][0] = a;
				dp[i][1] = 1;
			} else {
				dp[i][0] = b;
				dp[i][1] = 2;
			}
			if (c > dp[i][0]) {
				dp[i][0] = c;
				dp[i][1] = 1;
			}

		}
		System.out.println(dp[n + 2][0]);
	}
}