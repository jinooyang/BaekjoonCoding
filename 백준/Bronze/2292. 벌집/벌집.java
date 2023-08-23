import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		long start = 1;
		long layer = 1;
		if (n == 1) {
			System.out.println(1);
			return;

		}
		while (start < n) {
			start += 6 * layer;
			layer++;
			if (n <= start) {
				System.out.println(layer);
				break;
			}
		}

	}
}