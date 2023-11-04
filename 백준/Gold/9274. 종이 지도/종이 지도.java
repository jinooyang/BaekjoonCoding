import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int R;
	static int C;
	static int ary[][];
	static int dp[][];

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String test = null;
		while ((test = br.readLine()) != null) {

			StringTokenizer st = new StringTokenizer(test);
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			ary = new int[N][M];
			dp = new int[N][M];

			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < M; j++) {
					char x = s.charAt(j);
					if (x == '.')
						ary[i][j] = 0;
					else
						ary[i][j] = 1;

					if (j == 0)
						dp[i][j] = ary[i][j];
					else
						dp[i][j] = dp[i][j - 1] + ary[i][j];

				}
			}
			int answer = Integer.MAX_VALUE;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					int ans = countArea(i, j);
					answer = Math.min(ans, answer);
				}
			}
			sb.append(answer).append("\n");

		}

		System.out.println(sb);

	}

	private static int countArea(int row, int col) {
		int result = 0;
		int down = -row;
		while (down < N) {
			int across = -col;
			while (across < M) {

				int res = count(down, across);
				if (res > 0)
					result++;
				across += C;
			}
			down += R;
		}
		return result;
	}

	private static int count(int down, int across) {
		int i = Math.max(0, down);
		int j = Math.max(0, across);

		int ir = Math.min(N, down + R);
		int jc = Math.min(M, across + C);
		int a = 0;
		for (int y = i; y < ir; y++) {
			if (j == 0) {
				a += dp[y][jc - 1];
			} else {
				a += dp[y][jc - 1] - dp[y][j - 1];
			}
		}

		return a;
	}
}