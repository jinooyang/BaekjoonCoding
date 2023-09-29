import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int len = s.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= len; i++) {
			sb.append(s.charAt(i - 1));
			if(i%10==0)sb.append("\n");
		}
		System.out.println(sb);
	}
}