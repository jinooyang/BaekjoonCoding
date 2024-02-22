import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[] ary = new int[2 * M - 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int index = 0;
			int zero = Integer.parseInt(st.nextToken());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());

			index += zero;
			if (one != 0) {
				ary[index] += 1;
				index += one;
			}
			if (two != 0) {
				if (one == 0) {
					ary[index] += 2;
				} else
					ary[index] += 1;
			}
		}
		for (int i = 2*M-1 - 2; i >= 0; i--) {
			int num = ary[i];
			for (int j = i + 1; j < 2*M-1; j++) {
				ary[j] += num;
			}
		// System.out.println(Arrays.toString(ary));
		}
		for (int i = 0; i < 2*M-1; i++) {
			ary[i]++;
		}

		int answer[][] = new int[M][M];
		for (int i = 0; i < M; i++) {
			answer[M - 1 - i][0] = ary[i];
		}
		StringBuilder sb = new StringBuilder();
		for (int k = 0; k < M; k++) {
			sb.append(ary[M-1-k]).append(" ");
			for (int i = M; i < 2 * M - 1; i++) {
				sb.append(ary[i]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}