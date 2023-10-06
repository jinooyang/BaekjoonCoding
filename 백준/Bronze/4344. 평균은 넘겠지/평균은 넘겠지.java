import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int ary[] = new int[x];
			double avg = 0;
			for (int i = 0; i < x; i++) {
				ary[i] = Integer.parseInt(st.nextToken());
				avg += ary[i];
			}
			avg = avg / x;
			double cnt = 0;
			for (int i = 0; i < x; i++) {
				if (ary[i] > avg) {
					cnt++;
				}
			}
			System.out.printf("%.3f%%\n", (cnt / x) * 100);
			
		}
	}
}