import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String n = st.nextToken();
			if (n.equals("0"))
				break;
			boolean x = palindrome(n);
			if(x)System.out.println("yes");
			else System.out.println("no");
		}
	}

	private static boolean palindrome(String n) {
		boolean result = true;
		for (int i = 0; i < n.length() / 2; i++) {
			if (n.charAt(i) != n.charAt(n.length() - 1 - i))
				result = false;
		}
		return result;
	}

}