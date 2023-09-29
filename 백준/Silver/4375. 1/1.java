import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = null;
		StringBuilder sb = new StringBuilder();
		while ((s = br.readLine()) != null) {
			BigInteger i = new BigInteger(s);
			BigInteger one = new BigInteger("1");
			int answer = 1;
			while (true) {
//				System.out.println(one);
				if (one.mod(i) == BigInteger.ZERO) {
					sb.append(answer).append("\n");
					break;
				} else {
					answer++;
					one = one.multiply(BigInteger.TEN);
					one = one.add(BigInteger.ONE);
				}
			}
		}
		System.out.println(sb);
	}
}