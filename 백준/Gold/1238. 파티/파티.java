import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int vertex;
		int dist;

		public Node(int vertex, int dist) {
			super();
			this.vertex = vertex;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {

			return Integer.compare(this.dist, o.dist);
		}

	}

	static List<List<Node>> graph = new ArrayList<>();
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n + 1; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph.get(u).add(new Node(v, w));
		}

		int answerDistance[] = new int[n + 1];

		int distance[] = dijkstra(start);
		for (int i = 1; i <= n; i++) {
			answerDistance[i] += distance[i];
		}
		int maxAnswer = 0;
		for (int i = 1; i < n + 1; i++) {
			if (i != start) {
				int tempDistance[] = dijkstra(i);
				answerDistance[i] += tempDistance[start];
				if (answerDistance[i] > maxAnswer) {
					maxAnswer = answerDistance[i];
				}
			}
		}
		System.out.println(maxAnswer);

	}

	private static int[] dijkstra(int start) {
		pq.clear();
		int distance[] = new int[n + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		pq.add(new Node(start, 0));
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int now = node.vertex;
			int dist = node.dist;
			if (dist > distance[now]) {
				continue;
			}
			for (Node nextnode : graph.get(now)) {
				int newDist = distance[now] + nextnode.dist;
				if (newDist < distance[nextnode.vertex]) {
					distance[nextnode.vertex] = newDist;
					pq.add(new Node(nextnode.vertex, newDist));
				}
			}
		}
		return distance;
	}
}