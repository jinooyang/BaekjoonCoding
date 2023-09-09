import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static class Node {
		Map<String, Node> map = new TreeMap<>();
		boolean end;
	}

	static class Trie {
		Node root = new Node();

		public void insert(List<String> list) {
			Node node = this.root;
			for (int i = 0; i < list.size(); i++) {

				String s = list.get(i);
				if (!node.map.containsKey(s)) {
					node.map.put(s, new Node());
				}

				node = node.map.get(s);

			}
			node.end = true;
		}

		public void printTrie(int cnt, Node node) {
			for (String s : node.map.keySet()) {
				for (int i = 0; i < cnt; i++)
					sb.append("--");
				sb.append(s).append("\n");
				printTrie(cnt + 1, node.map.get(s));
				
			}
		}

	}

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		Trie trie = new Trie();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			List<String> list = new ArrayList<>();
			for (int j = 0; j < k; j++) {
				String s = st.nextToken();
				list.add(s);
			}
			trie.insert(list);
		}

		trie.printTrie(0, trie.root);
		System.out.println(sb);
	}
}