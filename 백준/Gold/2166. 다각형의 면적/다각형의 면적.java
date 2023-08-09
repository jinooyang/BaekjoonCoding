import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long x[] = new long[n + 1];
		long y[] = new long[n + 1];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		x[n] = x[0];
		y[n] = y[0];
		long suma = 0;
		long sumb = 0;
		for (int i = 0; i < n; i++) {
			suma += x[i] * y[i + 1];
			sumb += y[i] * x[i + 1];
		}
		System.out.printf("%.1f", (Math.abs(suma - sumb) / 2.0));
	}
}