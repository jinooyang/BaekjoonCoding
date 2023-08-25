import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String s = st.nextToken();
		boolean a = palindrome(s);
		if(a)System.out.println(1);
		else System.out.println(0);

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