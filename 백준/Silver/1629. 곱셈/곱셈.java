import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		long c = Long.parseLong(st.nextToken());

		// (a^b)%c
		// b = 10일때
		// (a^5 * a^5 )%c

		long answer = recursive(a, b, c);
		System.out.println(answer);
	}

	private static long recursive(long a, long b, long c) {
		if (b == 1)
			return a % c;
		long temp = recursive(a, b / 2, c);
		if (b % 2 == 0) {
			return temp%c * temp % c;
		} else {
			return (temp % c * temp % c) * a % c;
		}
	}
}

//(a*b)%c = (a%c*b%c)%c