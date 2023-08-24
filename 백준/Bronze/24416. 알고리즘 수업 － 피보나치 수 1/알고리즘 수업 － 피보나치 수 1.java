import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int ans1;
	static int ans2;
	static int f[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		f = new int[n * n];
		ans1 = 0;
		ans2 = 0;
		fib(n);
		f[1] = f[2] = 1;
		fibonacci(n);
		System.out.println(ans1 + " " + ans2);
	}

	private static int fibonacci(int n) {

		for (int i = 3; i <= n; i++) {
			ans2++;
			f[i] = f[i - 1] + f[i - 2];
		} // # 코드2
		return f[n];
	}

	private static int fib(int n) {
		if (n == 1 || n == 2) {
			ans1++;
			return 1; // # 코드1
		} else
			return (fib(n - 1) + fib(n - 2));
	}
}