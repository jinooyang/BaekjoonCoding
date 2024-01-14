import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static class Node implements Comparable<Node> {
		int x;
		int nodeNumbder;
		int isStart;

		public Node(int x, int nodeNumbder, int isStart) {
			super();
			this.x = x;
			this.nodeNumbder = nodeNumbder;
			this.isStart = isStart;
		}

		@Override
		public int compareTo(Node o) {
			if (this.x == o.x)
				return Integer.compare(this.isStart, o.isStart);
			else
				return Integer.compare(this.x, o.x);
		}

	}

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		List<Node> nodes = new ArrayList<>();

		for (int i = 1; i <= n; i++) {

			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			nodes.add(new Node(a, i, 1));
			nodes.add(new Node(b, i, 0));

		}

		Collections.sort(nodes);
		int nodeCnt = 0;
		int maxNodeCnt = 0;
		for (int i = 0; i < nodes.size(); i++) {
			int isStart = nodes.get(i).isStart;
			if (isStart == 1) {
				nodeCnt++;
				maxNodeCnt = Math.max(maxNodeCnt, nodeCnt);
			} else {
				nodeCnt--;
			}
		}
		System.out.println(maxNodeCnt);
	}
}