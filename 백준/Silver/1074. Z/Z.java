import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int answer = 0;
		while (true) {
			if (r == 0 && c == 0) {
				break;
			}

			int sq = getBiggestSq(r, c); // 가장 큰 2^n의 n

			int maxsq = 1 << sq; // 2^n using bitwise left shift
			int mul = 0;
			if (r >= maxsq) {
				mul += 2;
				r -= maxsq;
			}
			if (c >= maxsq) {
				mul += 1;
				c -= maxsq;
			}
			answer += maxsq * maxsq * mul;
		}
		System.out.println(answer);
	}

	private static int getBiggestSq(int r, int c) {
		int answer = 0;
		while (true) {
			int k = 1 << (answer + 1);
			if (k > r && k > c)
				break;
			else
				answer++;
		}
		return answer;
	}
}