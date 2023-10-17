import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		String s;
		int len;
		int cnt;

		public Node(String s, int len, int cnt) {
			super();
			this.s = s;
			this.len = len;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			if (this.cnt == o.cnt) {
				if (this.len == o.len) {
					return this.s.compareTo(o.s);
				} else {
					return Integer.compare(o.len, this.len);
				}
			} else
				return Integer.compare(o.cnt, this.cnt);
		}

	}

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			if (s.length() >= m) {
				if (!map.containsKey(s))
					map.put(s, 0);
				map.put(s, map.get(s) + 1);
			}
		}
		List<Node> list = new ArrayList<>();
		for (String s : map.keySet()) {
			list.add(new Node(s, s.length(), map.get(s)));
		}
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i).s).append("\n");
		}
		System.out.println(sb);
	}
}