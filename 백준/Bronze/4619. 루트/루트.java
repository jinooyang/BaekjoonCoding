import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.FileInputStream;

public class Main {
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		while (true) {
			st = new StringTokenizer(br.readLine());
			int b = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());

			if (b == 0 && n == 0)
				break;

			int minDiff = Integer.MAX_VALUE;
			int answer = 0;

			for (int a = 0; a <= b; a++) {
				int an = (int) Math.pow(a, n);
				int diff = Math.abs(b - an);
				if (minDiff > diff) {
					minDiff = diff;
					answer = a;
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
}