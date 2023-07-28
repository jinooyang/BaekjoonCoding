import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		for (int test = 0; test < t; test++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int ary[][] = new int[2][n + 2];
			int dp[][] = new int[2][n + 2];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 2; j < n + 2; j++)
					ary[i][j] = Integer.parseInt(st.nextToken());
			}
			for (int j = 2; j < n + 2; j++)
				for (int i = 0; i < 2; i++)
					dp[i][j] = ary[i][j] + Math.max(dp[(i + 1) % 2][j - 2], dp[(i + 1) % 2][j - 1]);
			bw.write(Math.max(dp[0][n + 1], dp[1][n + 1]) + "\n");
		}
		bw.flush();
		bw.close();
	}
}