import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(st.nextToken());
		int ary[] = { 25, 10, 5, 1 };
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			for (int idx = 0; idx < 4; idx++) {
				int cnt = 0;
				while (k >= ary[idx]) {
					cnt++;
					k -= ary[idx];
				}
				sb.append(cnt).append(" ");
			}
			sb.append("\n");

		}
		System.out.println(sb);
	}
}