import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		double ary[] = new double[n];
		double M = 0;
		for (int i = 0; i < n; i++) {

			ary[i] = Integer.parseInt(st.nextToken());
			if (ary[i] > M)
				M = ary[i];

		}
		double sum = 0;
		for (int i = 0; i < n; i++) {
			ary[i] =  ary[i] / M * 100;
			sum += ary[i];
		}
		System.out.println(sum / n);
	}
}