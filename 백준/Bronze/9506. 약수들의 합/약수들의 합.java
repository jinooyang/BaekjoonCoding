import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = null;
		while (true) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			if (n == -1)
				break;
			List<Integer> list = new ArrayList<>();
			boolean ans = check(n, list);
			if (ans) {
				System.out.print(n + " = ");
				for (int k = 0; k < list.size(); k++) {
					System.out.print(list.get(k));
					if (k != list.size() - 1) {
						System.out.print(" + ");
					}
				}
				System.out.println();
			} else
				System.out.println(n + " is NOT perfect.");
		}
	}

	private static boolean check(int n, List<Integer> list) {
		int total = 0;
		for (int i = 1; i < n; i++) {
			if (n % i == 0) {
				total += i;
				list.add(i);
			}
		}
		if (total == n)
			return true;
		else
			return false;
	}
}
