import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String s1 = st.nextToken();
		st = new StringTokenizer(br.readLine());
		String s2 = st.nextToken();
		BigInteger n = new BigInteger(s1);
		BigInteger m = new BigInteger(s2);
		
		System.out.println(n.add(m));
		System.out.println(n.subtract(m));
		System.out.println(n.multiply(m));
	}
}