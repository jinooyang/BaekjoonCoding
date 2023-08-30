import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int ary[][] = new int[30][30];

		for (int i = 1; i < 30; i++) {
			for (int j = i; j < 30; j++) {
				if (i == 1)
					ary[i][j] = j;
				else
					ary[i][j] = ary[i][j - 1] + ary[i - 1][j - 1];
			}
		}
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			System.out.println(ary[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]);
		}
	}
}