import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int ary[][] = new int[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			ary[i][0] = Integer.parseInt(st.nextToken());
			ary[i][1] = Integer.parseInt(st.nextToken());
		}
		int minDiff = Integer.MAX_VALUE;
		for (int i = 1; i < 1 << n; i++) {
			int sour = 1;
			int bitter = 0;
			for (int j = 0; j < n; j++) {
				if ((i & 1 << j) > 0) {
					sour *= ary[j][0];
					bitter += ary[j][1];
				}
			}
			if (Math.abs(sour - bitter) < minDiff) {
				minDiff = Math.abs(sour - bitter);
			}
		}
		System.out.println(minDiff);
	}
}