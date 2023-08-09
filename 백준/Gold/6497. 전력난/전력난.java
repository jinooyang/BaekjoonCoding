import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int parent[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		while (true) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			if (n == 0 && m == 0)
				break;
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
			int graph[][] = new int[m][3];
			int total = 0;
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				graph[i][0] = Integer.parseInt(st.nextToken());
				graph[i][1] = Integer.parseInt(st.nextToken());
				graph[i][2] = Integer.parseInt(st.nextToken());
				total += graph[i][2];
			}
			Arrays.sort(graph, (o1, o2) -> Integer.compare(o1[2], o2[2]));
			int answer = 0;

			for (int i = 0; i < m; i++) {
				if (find(graph[i][0]) != find(graph[i][1])) {
					answer += graph[i][2];
					union(graph[i][0], graph[i][1]);
				}
			}
			System.out.println(total - answer);

		}
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
		if (parent[i] == i)
			return i;
		else
			return find(parent[i]);
	}
}