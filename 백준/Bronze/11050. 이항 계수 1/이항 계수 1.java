import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		if (k >= 0 && n >= k) {
			int a = getFac(n);
			int b = getFac(k);
			int c = getFac(n - k);
			System.out.println(a / (b * c));
		} else if (k < 0) {
			System.out.println(0);
		} else if (k > n) {
			System.out.println(0);
		}
	}

	private static int getFac(int x) {
		int answer = 1;
		for (int i = 1; i <= x; i++) {
			answer *= i;
		}
		return answer;
	}
}