import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
	//	System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long m = Long.parseLong(st.nextToken());
		long ary[] = new long[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			ary[i] = Long.parseLong(st.nextToken());
		}
		long dp[] = new long[n];

		for (int i = 1; i < n; i++) {
			dp[i] = ary[i] + dp[i - 1];
		}

		long answer = 0;

		for (int i = 0; i < n; i++) {
			// System.out.println("--------------------");
			int movTime = i * 2;
			// System.out.println("movTime : " + movTime);
			long movHappiness = 0;
			if (i - 1 >= 0) {
				movHappiness = dp[i - 1] * 2 + ary[i];
			}
			// System.out.println("movHappiness : " + movHappiness);
			long timeLeft = m - movTime;
			if (timeLeft <= 0)
				break;
			// System.out.println("timeLeft : " + timeLeft);
			long currentHappiness = 0;
			if (i + 1 < n) {
				currentHappiness = (ary[i + 1] + ary[i]) * (timeLeft / 2);
			}
			currentHappiness = Math.max(0, currentHappiness);
			// System.out.println("currentHappiness : " + currentHappiness);
			long totalHappiness = movHappiness + currentHappiness;
			// System.out.println("totalHappiness : " + totalHappiness);
			answer = Math.max(totalHappiness, answer);
		}
		System.out.println(answer);
	}
}