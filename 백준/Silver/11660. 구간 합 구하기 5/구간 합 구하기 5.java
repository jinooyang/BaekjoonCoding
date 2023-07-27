import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static int n = 0;
	public static int m = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		// n*n 그래프
		int ary[][] = new int[n + 1][n + 1];
		int sumary[][] = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
				if (j == 1)
					sumary[i][j] = ary[i][j];
				else {
					sumary[i][j] = ary[i][j] + sumary[i][j - 1];
				}
			}

		}

		for (int i = 0; i < m; i++) {
			int answer = 0;
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			for (int x = x1; x <= x2; x++) {
				answer += sumary[x][y2] - sumary[x][y1 - 1];
			}
//			System.out.println(answer);
			bw.write(answer + "\n");

		}
		bw.flush();

		bw.close();
	}
}