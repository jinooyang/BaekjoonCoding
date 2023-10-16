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
		int answer = 0;
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			if (s.equals("ENTER")) {
				answer += set.size();
				set.clear();
				continue;
			}
			set.add(s);

		}
		answer+=set.size();
		System.out.println(answer);
	}
}
