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
		int n = Integer.parseInt(st.nextToken()) - 1;
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int answer = 0;

		while (true) {
			if (r == 0 && c == 0)
				break;
			if (r < n && c < n) {
				n--;
				continue;
			}
			int maxsq = 1 << n; // 2^n using bitwise left shift
			int mul = 0;
			if (r >= maxsq) {
				mul += 2;
				r -= maxsq;
			}
			if (c >= maxsq) {
				mul += 1;
				c -= maxsq;
			}
			answer += (1 << n << n) * mul;
			n -= 1;

		}
		System.out.println(answer);
	}

}