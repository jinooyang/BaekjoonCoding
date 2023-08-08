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
		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		graph = new int[m][3];
		parent = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < m; i++) {
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

		for (int i = 0; i < m; i++) {
			if (find(graph[i][0]) != find(graph[i][1])) {
				answer += graph[i][2];
				union(graph[i][0], graph[i][1]);
			}
		}
		System.out.println(answer);
	}

	private static void union(int i, int j) {
		int a = find(i);
		int b = find(j);
		if (a > b) {
			parent[a] = b;
		} else
			parent[b] = a;

	}

	private static int find(int i) {
		if (i == parent[i]) {
			return i;
		} else
			return find(parent[i]);
	}
}