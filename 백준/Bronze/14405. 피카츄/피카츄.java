import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		s = s.replace("pi", "..");
		s = s.replace("ka", "..");
		s = s.replace("chu", "...");
//		System.out.println(s);
		boolean answer = true;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != '.') {
				answer = false;
				break;
			}
		}
		if (answer)
			System.out.println("YES");
		else
			System.out.println("NO");
	}
}