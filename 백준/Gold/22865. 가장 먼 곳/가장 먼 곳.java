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
			// TODO Auto-generated method stub
			return Integer.compare(this.dist, o.dist);
		}

	}

	static int n;
	static List<List<Node>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int startA = Integer.parseInt(st.nextToken());
		int startB = Integer.parseInt(st.nextToken());
		int startC = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Node(v, w));
			graph.get(v).add(new Node(u, w));
		}

		int distanceA[] = dijkstra(startA);
		int distanceB[] = dijkstra(startB);
		int distanceC[] = dijkstra(startC);

		int maxDist = 0;
		int maxIndex = -1;
		for (int i = 1; i < n + 1; i++) {
			int temp = Math.min(distanceA[i], distanceB[i]);
			temp = Math.min(temp, distanceC[i]);
			if (temp > maxDist) {
				maxDist = temp;
				maxIndex = i;
			}
		}
		System.out.println(maxIndex);

	}

	private static int[] dijkstra(int start) {
		int distance[] = new int[n + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		distance[start] = 0;
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int now = node.vertex;
			int dist = node.dist;
			if (dist > distance[now]) {
				continue;
			}
			for (Node nextNode : graph.get(now)) {
				int newDist = distance[now] + nextNode.dist;
				if (newDist < distance[nextNode.vertex]) {
					distance[nextNode.vertex] = newDist;
					pq.add(new Node(nextNode.vertex, newDist));
				}
			}
		}
		return distance;
	}
}