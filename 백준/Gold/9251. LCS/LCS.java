import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String s2 = br.readLine();
		int l1 = s1.length();
		int l2 = s2.length();

		int dp[][] = new int[l2 + 1][l1 + 1];

		for (int j = 1; j <= l1; j++) {
			for (int i = 1; i <= l2; i++) {
				int temp = Math.max(dp[i-1][j], dp[i][j-1]);
				int temp2=0;
				if(s1.charAt(j-1) == s2.charAt(i-1)) {
					temp2 = dp[i-1][j-1]+1;
				}
				dp[i][j] = Math.max(temp, temp2);
			}
		}
		System.out.println(dp[l2][l1]);
	}
}