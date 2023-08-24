import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			int max = 2 * n - 1;
			for (int j = 0; j < max / 2 - i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < 2 * i + 1; j++) {
				System.out.print("*");
			}
//			for (int j = 0; j < max / 2; j++) {
//				System.out.print(" ");
//			}
			System.out.println();
		}
		for (int i = n - 2; i >= 0; i--) {
			int max = 2 * n - 1;
			for (int j = 0; j < max / 2 - i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < 2 * i + 1; j++) {
				System.out.print("*");
			}
//			for (int j = 0; j < max / 2; j++) {
//				System.out.print(" ");
//			}
			System.out.println();
		}
	}
}