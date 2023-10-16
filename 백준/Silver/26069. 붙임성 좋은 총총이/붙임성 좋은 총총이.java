import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		Set<String> set = new HashSet<>();
		boolean found = false;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			String b = st.nextToken();
			if (a.equals("ChongChong")) {
				found = true;
				set.add(a);
			}

			if (b.equals("ChongChong")) {
				found = true;
				set.add(b);
			}
			if (found)
				if (set.contains(a) || set.contains(b)) {
					set.add(a);
					set.add(b);
				}

		}
		System.out.println(set.size());

	}
}