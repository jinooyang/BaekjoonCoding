import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.FileInputStream;

public class Main {
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long n = Long.parseLong(st.nextToken());
		if (n == 0) {
			System.out.println(0);
			return;
		}
		long num = 1;
		long ans = 1;
		while (num < n) {
			num = num * 2;
			ans++;
		}
		System.out.println(ans);
	}
}