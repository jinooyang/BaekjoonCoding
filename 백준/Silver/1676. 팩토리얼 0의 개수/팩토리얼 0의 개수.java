import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		BigInteger fac = new BigInteger("1");
		for (int i = 2; i <= n; i++) {
			fac = fac.multiply(new BigInteger(i + ""));
		}
		//System.out.println(fac);
		int answer = 0;
		while (fac.compareTo(BigInteger.ZERO) > 0) {
			if (fac.mod(BigInteger.TEN) == BigInteger.ZERO) {
				answer++;
				fac = fac.divide(BigInteger.TEN);
			} else
				break;
		}
		System.out.println(answer);
	}
}