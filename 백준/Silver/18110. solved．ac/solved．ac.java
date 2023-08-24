import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int ary[] = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			ary[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(ary);
		int idx = avgIdx(n);
		double sum = 0;
		for (int i = idx; i < n - idx; i++) {
			sum += ary[i];
		}
		sum = sum / (n-idx-idx);
		sum = Math.round(sum);
		System.out.println((int)sum);
	}

	private static int avgIdx(int n) {
		double x = n;
		x = x * 0.15;
		x = Math.round(x);
		return (int) x;
	}
}