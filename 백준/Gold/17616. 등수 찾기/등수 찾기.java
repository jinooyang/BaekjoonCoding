import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		List<List<Integer>> graph = new ArrayList<>();
		List<List<Integer>> reverseGraph = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
			reverseGraph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph.get(a).add(b);
			reverseGraph.get(b).add(a);
		}

		int U = bfs(reverseGraph, X, N);// X보다 큰 개수....가장 높은 등수 u
		int V = bfs(graph, X, N); // X보다 작은 개수....가장 낮은 등수V

		V = N - V;
		U = U + 1;
		
		System.out.println(U + " " + V);

	}

	private static int bfs(List<List<Integer>> graph, int x, int n) {

		Queue<Integer> q = new ArrayDeque<>();
		boolean visited[] = new boolean[n + 1];
		int cnt = 0;
		q.add(x);
		visited[x] = true;
		while (!q.isEmpty()) {
			int now = q.poll();

			for (Integer next : graph.get(now)) {
				if (!visited[next]) {
					q.add(next);
					visited[next] = true;
				}
			}
		}
		for (int i = 0; i < n + 1; i++) {
			if (visited[i])
				cnt++;
		}
		return cnt - 1;// 자기 자신은 제외한다
	}
}