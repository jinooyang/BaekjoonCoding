import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int graph[][];
	static int parent[];

	static class QueueNode {
		int i;
		int min;

		public QueueNode(int i, int min) {
			super();
			this.i = i;
			this.min = min;
		}

	}

	static class GraphNode {
		int to;
		int dist;

		public GraphNode(int to, int dist) {
			super();
			this.to = to;
			this.dist = dist;
		}

	}

	static class SetNode {
		int i;
		int j;
		int dist;

		public SetNode(int i, int j, int dist) {
			super();
			this.i = i;
			this.j = j;
			this.dist = dist;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + dist;
			result = prime * result + i;
			result = prime * result + j;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SetNode other = (SetNode) obj;
			if (dist != other.dist)
				return false;
			if (i != other.i)
				return false;
			if (j != other.j)
				return false;
			return true;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		graph = new int[m][3];
		parent = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			parent[i] = i;
		}
		List<List<GraphNode>> list = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			graph[i][0] = Integer.parseInt(st.nextToken());
			graph[i][1] = Integer.parseInt(st.nextToken());
			graph[i][2] = Integer.parseInt(st.nextToken());
			list.get(graph[i][0]).add(new GraphNode(graph[i][1], graph[i][2]));
			list.get(graph[i][1]).add(new GraphNode(graph[i][0], graph[i][2]));
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		Arrays.sort(graph, (o1, o2) -> Integer.compare(o2[2], o1[2]));
		Set<SetNode> set = new HashSet<>();
		for (int i = 0; i < m; i++) {
			int x = graph[i][0];
			int y = graph[i][1];

			if (find(x) != find(y)) {
				set.add(new SetNode(x, y, graph[i][2]));
				set.add(new SetNode(y, x, graph[i][2]));
				union(x, y);
			}
		}

		Queue<QueueNode> q = new ArrayDeque<>();
		q.add(new QueueNode(start, Integer.MAX_VALUE));
		boolean visited[] = new boolean[n + 1];
		visited[start] = true;
		int answer = 0;
		while (!q.isEmpty()) {
			QueueNode now = q.poll();
			if (now.i == end) {
				answer = now.min;
				break;
			}
			for (GraphNode k : list.get(now.i)) {
				if (!visited[k.to] && find(k.to) == find(now.i) && set.contains(new SetNode(k.to, now.i, k.dist))) {
					q.add(new QueueNode(k.to, Math.min(now.min, k.dist)));
					visited[k.to] = true;
				}
			}
		}
		System.out.println(answer);
	}

	public static void union(int x, int y) {
		int a = find(x);
		int b = find(y);
		if (a > b)
			parent[a] = b;
		else
			parent[b] = a;

	}

	public static int find(int x) {
		if (parent[x] == x)
			return x;
		else
			return parent[x] = find(parent[x]);
	}
}