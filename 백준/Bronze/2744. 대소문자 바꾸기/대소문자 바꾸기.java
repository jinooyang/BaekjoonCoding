import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String s = st.nextToken();
		for (int i = 0; i < s.length(); i++) {
			Character x = s.charAt(i);
			if (x >= 'a')
				x = x.toUpperCase(x);
			else
				x = x.toLowerCase(x);
			System.out.print(x);
		}
	}
}
