import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int parent[];

	static class Node implements Comparable<Node> {
		int start;
		int end;
		int dist;

		public Node(int start, int end, int dist) {
			super();
			this.start = start;
			this.end = end;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.dist, o.dist);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		parent = new int[N + 1];
		char gender[] = new char[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			gender[i] = st.nextToken().charAt(0);
			parent[i] = i;

		}
		List<Node> graph = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			if (gender[u] != gender[v]) {
				Node node = new Node(u, v, w);
				graph.add(node);
			}
		}
		Collections.sort(graph);
		int answer = 0;
		for (int i = 0; i < graph.size(); i++) {
			if (find(graph.get(i).start) != find(graph.get(i).end)) {
				answer += graph.get(i).dist;
				union(graph.get(i).start, graph.get(i).end);
			}
		}
		int k = find(1);
		boolean isAnswer = true;
		for (int i = 1; i <= N; i++) {
			if (find(parent[i]) != k)
				isAnswer = false;
		}
		if (!isAnswer)
			System.out.println(-1);
		else
			System.out.println(answer);

	}

	private static void union(int start, int end) {
		int a = find(start);
		int b = find(end);
		if (a > b)
			parent[a] = b;
		else
			parent[b] = a;

	}

	private static int find(int x) {
		if (x == parent[x])
			return x;
		else
			return find(parent[x]);
	}
}