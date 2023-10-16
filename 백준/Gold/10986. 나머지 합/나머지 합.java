import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		long sumDP[] = new long[n];
		long modDP[] = new long[m];
		modDP[0] = 1;
		long answer = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int x = Integer.parseInt(st.nextToken());
			if (i >= 1) {
				sumDP[i] = sumDP[i - 1];
			}
			sumDP[i] += x;
			long mod = sumDP[i] % m;
			answer += modDP[(int) mod];
			// System.out.println("mod : " + mod + " , answer = " + answer);
			modDP[(int) mod]++;
		}
		// System.out.println(Arrays.toString(modDP));
		System.out.println(answer);

	}
}