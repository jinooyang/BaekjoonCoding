import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		long ary[] = new long[n];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(getLCM(ary) - k);

	}

	public static long gcd(long a, long b) {
		BigInteger b1 = BigInteger.valueOf(a);
		BigInteger b2 = BigInteger.valueOf(b);
		BigInteger gcd = b1.gcd(b2);
		return gcd.intValue();
	}

	public static long getLCM(long[] arr) {
		if (arr.length == 1) {
			return arr[0];
		}

		long gcd = gcd(arr[0], arr[1]);
		long lcm = (arr[0] * arr[1]) / gcd;

		for (int i = 2; i < arr.length; i++) {
			gcd = gcd(lcm, arr[i]);
			lcm = (lcm * arr[i]) / gcd;
		}

		return lcm;
	}

}