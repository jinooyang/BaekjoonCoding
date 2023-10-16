import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		if (n == 1) {
			System.out.println(1);
			return;
		}

		if (n == 2) {
			if (m % 2 == 1) {
				System.out.println(Math.min(m / 2 + 1, 4));
				return;
			}
			if (m % 2 == 0) {
				System.out.println(Math.min(m / 2, 4));
				return;
			}
		}
		if (n >= 3) {
			if (m >= 7) {
				System.out.println(m - 2);
				return;
			} else {
				System.out.println(Math.min(m, 4));
				return;
			}
		}

	}
}