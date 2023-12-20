import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.FileInputStream;

public class Main {
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		String s = Integer.toBinaryString(n);
//		System.out.println(s);
		for (int i = 0; i < s.length(); i++) {
			char now = s.charAt(s.length() - 1 - i);
			if(now == '1')
				System.out.print(i + " ");
		}
	}
}