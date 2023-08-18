import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static Map<String, Integer> root;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(st.nextToken());

		for (int t = 0; t < tc; t++) {
			Set<String> names = new HashSet<>();
			Map<String, String> parent = new HashMap<>();
			root = new HashMap<>();
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			for (int i = 0; i < f; i++) {
				st = new StringTokenizer(br.readLine());
				String name1 = st.nextToken();
				if (!names.contains(name1)) {
					names.add(name1);
					parent.put(name1, name1);
					root.put(name1, 1);
				}
				String name2 = st.nextToken();
				if (!names.contains(name2)) {
					names.add(name2);
					parent.put(name2, name2);
					root.put(name2, 1);

				}

				if (find(name1, parent) != find(name2, parent)) {
					union(name1, name2, parent);
				}
				String cycle = find(name1, parent);
				int answer = root.get(cycle);

//				for (String key : parent.keySet()) {
//					if (cycle.equals(find(key, parent))) {
//						answer++;
//					}
//				}
				sb.append(answer).append("\n");
			}

		}
		System.out.println(sb.toString());
	}

	private static void union(String name1, String name2, Map<String, String> parent) {
		String a = find(name1, parent);
		String b = find(name2, parent);
		if (a.compareTo(b) < 0) {
			root.put(a, root.get(a) + root.get(b));
			parent.put(b, a);
		} else {
			root.put(b, root.get(a) + root.get(b));
			parent.put(a, b);
		}
	}

	private static String find(String name, Map<String, String> parent) {
		String originalParent = parent.get(name);
		if (originalParent == name)
			return name;
		else {
			String newParent = find(parent.get(name), parent);
			parent.put(name, newParent);
			return newParent;
		}
	}
}