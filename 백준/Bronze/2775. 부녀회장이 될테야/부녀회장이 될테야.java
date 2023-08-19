import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		int ary[][] = new int[15][15];
		for (int x = 1; x < 15; x++) {
			ary[0][x] = x;
		}
		for (int i = 1; i < 15; i++) {
			for (int j = 1; j < 15; j++) {
				ary[i][j] = sum(i, j, ary);
			}
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			// k층 n호에는 누가살아?
			System.out.println(ary[k][n]);
		}
	}

	private static int sum(int a, int b, int[][] ary) {
		int sum = 0;

		for (int j = 1; j <= b; j++) {
			sum += ary[a - 1][j];
		}

		return sum;
	}
}