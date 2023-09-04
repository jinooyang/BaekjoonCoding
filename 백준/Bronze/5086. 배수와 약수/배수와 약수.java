import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		while (true) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			if (n == 0 && m == 0)
				break;
			if (n!=0 && m % n == 0)
				System.out.println("factor");
			else if (m!=0 && n % m == 0)
				System.out.println("multiple");
			else
				System.out.println("neither");
		}
	}
}