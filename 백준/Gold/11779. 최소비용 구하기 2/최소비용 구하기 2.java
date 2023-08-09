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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		List<List<Node>> graph = new ArrayList<>();
		List<List<Integer>> route = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			graph.add(new ArrayList<>());
			route.add(new ArrayList<>());
		}
		int distance[] = new int[n + 1];

		Arrays.fill(distance, Integer.MAX_VALUE);
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph.get(u).add(new Node(v, w));
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		distance[start] = 0;
		route.get(start).add(start);
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
					route.get(nextNode.vertex).clear();
					route.get(nextNode.vertex).addAll(route.get(now));
					route.get(nextNode.vertex).add(nextNode.vertex);
					pq.add(new Node(nextNode.vertex, newDist));

				}
			}
		}

		System.out.println(distance[end]);
		System.out.println(route.get(end).size());
		for (int i = 0; i < route.get(end).size(); i++) {
			System.out.print(route.get(end).get(i) + " ");
		}

	}
}