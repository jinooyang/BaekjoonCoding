import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int max = -1;
		int ai = 0;
		int aj = 0;
		for (int i = 1; i <= 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				int x = Integer.parseInt(st.nextToken());
				if (x > max) {
					max = x;
					ai = i;
					aj = j;
				}
			}
		}
		System.out.println(max);
		System.out.println(ai + " " + aj);
	}
}