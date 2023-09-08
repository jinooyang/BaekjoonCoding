import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	static class Node implements Comparable<Node> {
		String s;
		int cnt;

		public Node(String s, int cnt) {
			this.s = s;
			this.cnt = cnt;
		}

		public int compareTo(Node o) {
			return this.s.compareTo(o.s);
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		Map<String, Integer> map = new HashMap<>();
		int total = 0;
		while(sc.hasNext()) {
			total++;
			String s = sc.nextLine();
			if (map.containsKey(s)) {
				map.put(s, map.get(s) + 1);
			} else {
				map.put(s, 1);
			}
		}
		sc.close();
		List<Node> list = new ArrayList<>();

		for (String s : map.keySet()) {
			list.add(new Node(s, map.get(s)));
		}

		Collections.sort(list);

		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i).s + " ");
			System.out.printf("%.4f", ((float)list.get(i).cnt / total)*100);
			System.out.println();
		}

	}
}