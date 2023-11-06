import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		for (int T = 0; T < t; T++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int ary[] = new int[x];

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < x; j++) {
				ary[j] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(ary);
			System.out.println((ary[x - 1] - ary[0]) * 2);
		}
	}
}
