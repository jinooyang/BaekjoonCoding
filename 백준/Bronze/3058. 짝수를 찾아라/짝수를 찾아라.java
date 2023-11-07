import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int minEven = Integer.MAX_VALUE;
			int sum = 0;
			for (int j = 0; j < 7; j++) {
				int x = Integer.parseInt(st.nextToken());
				if (x % 2 == 0) {
					minEven = Math.min(minEven, x);
					sum += x;
				}
			}
			System.out.println(sum + " " + minEven);

		}
	}
}