import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		Double x;
		Double y;

		public Node(Double x, Double y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}

	}

	static int parent[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		List<Node> nodeList = new ArrayList<>();
		parent = new int[n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			Double x = Double.parseDouble(st.nextToken());
			Double y = Double.parseDouble(st.nextToken());
			nodeList.add(new Node(x, y));
			parent[i] = i;
		}

		int graphSize = n * (n - 1) / 2;
		double graph[][] = new double[graphSize][3];

		int idx = 0;
		for (int i = 0; i < nodeList.size() - 1; i++) {
			for (int j = i + 1; j < nodeList.size(); j++) {
				Node a = nodeList.get(i);
				Node b = nodeList.get(j);

				double dist = Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
				graph[idx][0] = i;// start
				graph[idx][1] = j;// end
				graph[idx][2] = dist;// dist
				idx++;
			}
		}
		Arrays.sort(graph, (o1, o2) -> Double.compare(o1[2], o2[2]));

		double answer = 0;
		for (int i = 0; i < graphSize; i++) {
			if (find(graph[i][0]) != find(graph[i][1])) {
				answer += graph[i][2];
				union(graph[i][0], graph[i][1]);
			}
		}
		System.out.printf("%.2f", answer);

	}

	private static void union(double d, double e) {
		int a = find(d);
		int b = find(e);
		if (a > b)
			parent[a] = b;
		else
			parent[b] = a;
	}

	private static int find(double d) {
		int x = (int) d;
		if (parent[x] == x)
			return x;
		else
			return find(parent[x]);
	}
}