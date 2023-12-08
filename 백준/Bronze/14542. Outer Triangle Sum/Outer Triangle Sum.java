import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.FileInputStream;

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		String s = null;
		StringBuilder sb = new StringBuilder();
		int test_case = 1;
		while ((s = br.readLine()) != null) {
			st = new StringTokenizer(s);
			int n = Integer.parseInt(st.nextToken());
			if (n == 0)
				break;
			int sum = 0;
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < i; j++) {
					int x = Integer.parseInt(st.nextToken());
					if (i < n)
						if (j == 0 || j == i - 1) {
							
							sum += x;
						}
					if (i == n) {
						sum += x;
					}

				}
			}
			sb.append("Case #").append(test_case).append(":").append(sum).append("\n");
			test_case++;
		}
		System.out.println(sb);
	}
}