import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int W[][];
	static int dp[][];
	static final int maxval = 100_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		W = new int[n][n];
		dp = new int[n][1 << n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(dp[i], -1);
		}
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
				if (W[i][j] == 0)
					W[i][j] = maxval;// 못가는 거리는 1억으로
			}
		}

		// System.out.println("answer : " + dfs(1, 1, 0));
		System.out.println(dfs(1, 1, 0));
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < 1 << n; j++) {
//				System.out.print(dp[i][j] == maxval ? 0 + " " : dp[i][j] + " ");
//			}
//			System.out.println();
//		}
	}

	// 마지막 추가된 노드부터 1까지 거리를 리턴한다
	private static int dfs(int cnt, int bitMask, int start) {
		if (cnt == n) {// 여기부터 0까지 거리를 1111에 저장한다
			dp[start][bitMask] = W[start][0];
			return dp[start][bitMask];
		}
		// 재귀
		if (dp[start][bitMask] != -1) {// 비트마스크 조합으로 0까지 가는 거리를 알고있으면 그대로 반환
			return dp[start][bitMask];// 방문했던 값을 리턴한다
		}

		for (int i = 1; i < n; i++) {// 모르면 계산해서 최소값 찾아서 반환
			if ((bitMask & (1 << i)) == 0) {// 포함이 안된 수만 넣는다
				if (dp[i][bitMask | (1 << i)] == -1)// 방문을 하지 않았다
					dp[i][bitMask | (1 << i)] = dfs(cnt + 1, bitMask | (1 << i), i);
				if (dp[start][bitMask] != -1)
					dp[start][bitMask] = Integer.min(dp[start][bitMask], W[start][i] + dp[i][bitMask | (1 << i)]);
				else {
					dp[start][bitMask] = W[start][i] + dp[i][bitMask | (1 << i)];
				}
			}
		}
		return dp[start][bitMask];
	}
}