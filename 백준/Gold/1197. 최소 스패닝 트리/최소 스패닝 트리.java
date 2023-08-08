import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int graph[][];
	static int parent[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		graph = new int[E][3];
		parent = new int[V + 1];
		for (int i = 1; i < V; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph[i][0] = u;
			graph[i][1] = v;
			graph[i][2] = w;
		}
		Arrays.sort(graph, (o1, o2) -> Integer.compare(o1[2], o2[2]));
		int answer = 0;
		for (int i = 0; i < E; i++) {
			if (findParent(graph[i][0]) != findParent(graph[i][1])) {
				union(graph[i][0], graph[i][1]);
				answer += graph[i][2];
			}
		}
		System.out.println(answer);
	}

	private static void union(int x, int y) {
		int a = findParent(x);
		int b = findParent(y);
		if (a > b) {
			parent[a] = b;
		} else
			parent[b] = a;
	}

	private static int findParent(int node) {
		if (node == parent[node])
			return node;
		else
			return findParent(parent[node]);
	}
}