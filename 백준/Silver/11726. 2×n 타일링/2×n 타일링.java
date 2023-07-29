import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		int ary[] = new int[n + 2];
		ary[2] = 1;
		if (n > 1) {

			ary[3] = 2;
			for (int i = 4; i < n + 2; i++) {
				ary[i] = (ary[i - 1]%10007 + ary[i - 2]%10007)%10007;
			}
		}
		System.out.println(ary[n + 1]);

	}
}