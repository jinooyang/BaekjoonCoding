import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		Set<String> set = new HashSet<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String s = st.nextToken();
			if (s.equals("enter"))
				set.add(name);
			else
				set.remove(name);
		}
		List<String> list = new ArrayList<>();
		for(String s : set) {
			list.add(s);
		}
		Collections.sort(list,Collections.reverseOrder());
		StringBuilder sb  = new StringBuilder();
		for(int i=0;i<list.size();i++) {
			sb.append(list.get(i)).append("\n");
		}
		System.out.println(sb);
	}
}