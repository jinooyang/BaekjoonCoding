import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int ary[][];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		ary = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < n; j++) {
				ary[i][j] = s.charAt(j) - '0';
			}
		}

		// printAry(n);
		calc(0, 0, n);
		System.out.println(sb.toString());
	}

	private static void calc(int i, int j, int n) {
		// 현재 영역이 모두 0 혹은 1인지 확인
		int ones = 0;
		int zeros = 0;
		for (int p = i; p < i + n; p++) {
			for (int q = j; q < j + n; q++) {
				if (ary[p][q] == 1)
					ones++;
				else
					zeros++;
			}
		}
		if (zeros == 0) {
			sb.append(1);
			return;
		}
		if (ones == 0) {
			sb.append(0);
			return;
		} else {
			sb.append("(");
			calc(i, j, n / 2);
			calc(i, j + n / 2, n / 2);
			calc(i + n / 2, j, n / 2); 
			calc(i + n / 2, j + n / 2, n / 2);
			sb.append(")");
			return;
		}
	}

	private static void printAry(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(ary[i][j] + "");
			}
			System.out.println();
		}
	}

}