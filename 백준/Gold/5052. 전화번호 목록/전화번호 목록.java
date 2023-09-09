import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static class Node {
		Map<Character, Node> map = new TreeMap<>();
		boolean end;
	}

	static class Trie {
		Node root = new Node();

		public void insert(String s) {
			Node node = this.root;
			for (int i = 0; i < s.length(); i++) {
				if (!node.map.containsKey(s.charAt(i))) {
					node.map.put(s.charAt(i), new Node());
				}
				node = node.map.get(s.charAt(i));
			}

			node.end = true;

		}

		public boolean search(String s) {
			Node node = this.root;
			for (int i = 0; i < s.length(); i++) {
				if (node.end)
					return false;
				if (!node.map.containsKey(s.charAt(i))) {
					return true;
				}
				node = node.map.get(s.charAt(i));

			}
			if (node.map.size() > 0)
				return false;
			return true;
		}

	}

	static StringBuilder answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		answer = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			Trie tri = new Trie();
			boolean possible = true;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				String k = st.nextToken();
				if (!tri.search(k)) {
					possible = false;
				}
				tri.insert(k);
			}
			if (possible)
				answer.append("YES\n");
			else
				answer.append("NO\n");

		}
		System.out.println(answer);
	}
}