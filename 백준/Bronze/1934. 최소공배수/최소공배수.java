import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a % b == 0) {
				sb.append(a).append("\n");
				continue;
			} else if (b % a == 0) {
				sb.append(b).append("\n");
				continue;
			} else
				sb.append(lcm(a, b)).append("\n");
			continue;
		}
		System.out.println(sb.toString());
	}

	private static int lcm(int a, int b) {
		int x = Math.max(a, b);
		int y = Math.min(a, b);
		for (int i = 1; i <= y; i++) {
			if ((x * i) % a == 0 && (x * i) % b == 0) {
				return x * i;
			}
		}
		return 0;
	}
}
