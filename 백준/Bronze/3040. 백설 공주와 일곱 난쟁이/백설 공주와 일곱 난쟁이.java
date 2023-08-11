import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int ary[];
	static boolean found = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ary = new int[9];
		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			ary[i] = Integer.parseInt(st.nextToken());
		}
		int sum = 0;
		com(0, sum, -1);
	}

	private static void com(int cnt, int sum, int before) {
		if (cnt == 7) {
			if (sum == 100) {
				found = true;
			}
			return;
		}
		for (int i = before + 1; i < 9; i++) {
			com(cnt + 1, sum + ary[i], i);
			if (found) {
				System.out.println(ary[i]);
				return;
			}
		}
	}
}