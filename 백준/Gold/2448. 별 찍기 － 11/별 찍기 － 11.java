import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n;

	static char ary[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		ary = new char[n][(2 * n)];
		for (int i = 0; i < n; i++) {
			Arrays.fill(ary[i], ' ');
		}
		ary[0][n] = '*';
		ary[1][n - 1] = '*';
		ary[1][n + 1] = '*';
		for (int k = -2; k <= 2; k++) {
			ary[2][n + k] = '*';
		}
		// 3 * 2(3)-1
		// 6 * 2(6)-1
		makeStar(0, n, 3);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (2 * n); j++) {
				sb.append(ary[i][j]);
			}
			if (i != n - 1)
				sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void makeStar(int starti, int startj, int curHeight) {
		if (curHeight >= n)
			return;
		for (int i = curHeight; i < curHeight * 2; i++) {
			for (int j = startj - (2 * curHeight - 1); j < startj; j++) {
				ary[i][j] = ary[i - curHeight][j + curHeight];
			}
		}
		for (int i = curHeight; i < curHeight * 2; i++) {
			for (int j = startj + 1; j <= startj + (2 * curHeight - 1); j++) {
				ary[i][j] = ary[i - curHeight][j - curHeight];
			}
		}
		makeStar(0, n, curHeight * 2);

	}
}