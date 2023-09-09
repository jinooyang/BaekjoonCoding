import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String s = st.nextToken();
		String s2 = st.nextToken();

		BigInteger num1 = new BigInteger(s);
		BigInteger num2 = new BigInteger(s2);

		System.out.println(num1.divide(num2));
		System.out.println(num1.mod(num2));
	}
}