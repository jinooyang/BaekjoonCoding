import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Integer n = Integer.parseInt(st.nextToken());
		Integer m = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(n.toString());
		}
		String s = "";
		if (sb.length() > m) {
			s = sb.substring(0, m);
		}
		else {
			s = sb.toString();
		}
		System.out.println(s);

	}
}