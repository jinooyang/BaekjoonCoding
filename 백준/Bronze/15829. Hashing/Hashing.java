import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int getPower(int a, int b) {
		int mul = 1;
		for (int i = 0; i < b; i++) {
			mul = mul * a % 1234567891;
		}
		return mul;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		String s = st.nextToken();
		int sum = 0;
		for (int i = 0; i < s.length(); i++) {
			sum += (s.charAt(i) - 'a' + 1) * getPower(31, i) % 1234567891;
		}
		System.out.println(sum);

	}
}