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

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());// 정점
			int d = Integer.parseInt(st.nextToken());// 간선
			int start = Integer.parseInt(st.nextToken());// 시작점
			PriorityQueue<Node> pq = new PriorityQueue<>();
			List<List<Node>> graph = new ArrayList<>();
			for (int i = 0; i < n + 1; i++)
				graph.add(new ArrayList<>());
			int distance[] = new int[n + 1];
			Arrays.fill(distance, Integer.MAX_VALUE);
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				graph.get(b).add(new Node(a, c));
			}
			pq.add(new Node(start, 0));
			distance[start] = 0;
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
			int cnt = 0;
			int maxDist = 0;

			for (int i = 1; i < n + 1; i++) {
				if (distance[i] < Integer.MAX_VALUE) {
					cnt++;
					if (distance[i] > maxDist) {
						maxDist = distance[i];
					}
				}
			}
			System.out.println(cnt + " " + maxDist);
		}
	}
}