import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int i;
		int j;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

	}

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		int ary[] = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(ary);
		Map<Integer, List<Node>> map = new HashMap<>();
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				int x = ary[i] + ary[j];
//				System.out.println(x);
				if (!map.containsKey(x)) {
					map.put(x, new ArrayList<>());
				}
				map.get(x).add(new Node(i, j));
			}
		}
		int answer = 0;
		for (int i = 0; i < n; i++) {
			if (!map.containsKey(ary[i]))
				continue;
//			System.out.println("check index: " + i);
			boolean possible = false;
			for (Node k : map.get(ary[i])) {
//				System.out.println(k.i + " + " + k.j);
				if (k.i != i && k.j != i) {
					possible = true;
//					System.out.println("found");
					break;
				}
				
			}
			if (possible) {
//				System.out.println(ary[i]);
//				System.out.println(i);
				answer++;
			}
		}
		System.out.println(answer);
	}
}