import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static char answer[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		answer = new char[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(answer[i], ' ');
		}
		answer[0][0] = '*';
		star(1);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(answer[i][j]);
			}
			if (i != n - 1)
				sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void star(int pattern) {

		if (pattern >= n)
			return;
		addStar(0 + pattern, 0, pattern);
		addStar(0 + pattern * 2, 0, pattern);
		addStar(0, pattern, pattern);
//		addStar(0 + pattern, pattern, pattern);
		addStar(0 + pattern * 2, pattern, pattern);
		addStar(0, pattern * 2, pattern);
		addStar(0 + pattern, pattern * 2, pattern);
		addStar(0 + pattern * 2, pattern * 2, pattern);
		star(pattern * 3);
	}

	private static void addStar(int i, int j, int p) {
		for (int y = i; y < i + p; y++) {
			for (int x = j; x < j + p; x++) {
				answer[y][x] = answer[y - i][x - j];
			}
		}
	}
}