import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		funcA(1, 0);
	}

	private static void funcA(int finalNum, int len) {
		if (len == m) {
			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i) + " ");

			}
			System.out.println();
			return;
		}
		for (int i = finalNum; i <= n; i++) {
			list.add(i);
			funcA(i, len + 1);
			list.remove(list.size() - 1);
		}
	}
}