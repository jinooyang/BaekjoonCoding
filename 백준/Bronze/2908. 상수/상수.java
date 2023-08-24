import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String a = st.nextToken();
		String b = st.nextToken();
		String x = "";
		String y = "";
		for (int i = a.length() - 1; i >= 0; i--) {
			x += a.charAt(i);
		}
		for (int i = b.length() - 1; i >= 0; i--) {
			y += b.charAt(i);
		}
		int i = Integer.parseInt(x);
		int j = Integer.parseInt(y);
		System.out.println(Math.max(i,j));
	}
}