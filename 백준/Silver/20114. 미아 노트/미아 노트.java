import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		char ary[] = new char[n];
		for (int i = 0; i < n; i++) {
			ary[i] = '?';
		}
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			String tempc = st.nextToken();
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < w; k++) {
					if (!(tempc.charAt(j * w + k) == '?')) {
						ary[j] = tempc.charAt(j * w + k);
					}
				}
			}
		}
		for (int i = 0; i < n; i++) {
			System.out.print(ary[i]);
		}

	}
}