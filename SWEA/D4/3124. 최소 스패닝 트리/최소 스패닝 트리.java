import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static long graph[][];
	static long parent[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			graph = new long[E][3];
			parent = new long[V + 1];
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
			Arrays.sort(graph, (o1, o2) -> Long.compare(o1[2], o2[2]));
			long answer = 0;
			for (int i = 0; i < E; i++) {
				if (findParent(graph[i][0]) != findParent(graph[i][1])) {
					union(graph[i][0], graph[i][1]);
					answer += graph[i][2];
				}
			}
			System.out.println("#" + tc + " " + answer);
		}
	}

	private static void union(long x, long y) {
		long a = findParent(x);
		long b = findParent(y);
		if (a > b) {
			parent[(int) a] = b;
		} else
			parent[(int) b] = a;
	}

	private static long findParent(long node) {
		if (node == parent[(int) node])
			return node;
		else
			return findParent(parent[(int) node]);
	}
}