import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int parent[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		parent = new int[n + 1];
		for (int i = 0; i < n + 1; i++) {
			parent[i] = i;
		}
		int graph[][] = new int[m][3];
		long totalDist = 0;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			graph[i][0] = Integer.parseInt(st.nextToken());
			graph[i][1] = Integer.parseInt(st.nextToken());
			graph[i][2] = Integer.parseInt(st.nextToken());
			totalDist += graph[i][2];
		}
		Arrays.sort(graph, (o1, o2) -> Integer.compare(o1[2], o2[2]));
		long minDist = 0;
		for (int i = 0; i < m; i++) {
			if (find(graph[i][0]) != find(graph[i][1])) {
				minDist += graph[i][2];
				union((int) graph[i][0], (int) graph[i][1]);
			}
		}
		long answer = totalDist - minDist;
		int p = find(1);
		for (int i = 1; i < n + 1; i++) {
			if (p != find(i))
				answer = -1;
		}
		System.out.println(answer);
	}

	private static void union(int i, int j) {
		int a = find(i);
		int b = find(j);
		if (a > b)
			parent[a] = b;
		else
			parent[b] = a;
	}

	private static int find(int i) {
		if (parent[i] == i)
			return i;
		else
			return find(parent[i]);
	}
}