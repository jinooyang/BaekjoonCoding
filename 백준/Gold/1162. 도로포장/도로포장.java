import java.io.BufferedReader;
import java.io.FileInputStream;
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
		long dist;
		int k;

		public Node(int vertex, long dist, int k) {
			super();
			this.vertex = vertex;
			this.dist = dist;
			this.k = k;
		}

		@Override
		public int compareTo(Node o) {
//			if (this.k != o.k)
//				return Integer.compare(o.k, this.k);

			return Long.compare(this.dist, o.dist);
		}
	}

	public static void main(String[] args) throws IOException {
		// for (int t = 1; t <= 5; t++) {
		// System.setIn(new FileInputStream("testCase/revamp." + t + ".in"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		List<List<Node>> graph = new ArrayList<>();

		long distance[][] = new long[k + 1][n + 1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (int i = 0; i < n + 1; i++) {
			graph.add(new ArrayList<>());

		}
		for (int i = 0; i < k + 1; i++) {
			Arrays.fill(distance[i], Long.MAX_VALUE);
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Node(v, w, 0));
			graph.get(v).add(new Node(u, w, 0));
		}

		pq.add(new Node(1, 0, k));
		for (int i = 0; i < k + 1; i++) {
			distance[i][1] = 0;
		}
//k를 위한 priorityqueue를 따로 줘야하나?
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int now = node.vertex;
			long dist = node.dist;
			int nowk = node.k;
			if (dist > distance[nowk][now]) {
				continue;
			}
//k대신 nowk
			for (Node nextNode : graph.get(now)) {
				long newDist = distance[nowk][now] + nextNode.dist;
				if (newDist < distance[nowk][nextNode.vertex]) {
					distance[nowk][nextNode.vertex] = newDist;
					pq.add(new Node(nextNode.vertex, newDist, nowk));
				}
			}
			if (nowk > 0) {
				for (Node nextNode : graph.get(now)) {
					long newDist = distance[nowk][now];
					if (distance[nowk][nextNode.vertex] < distance[nowk - 1][nextNode.vertex]) {
						distance[nowk - 1][nextNode.vertex] = distance[nowk][nextNode.vertex];

					}
					if (distance[nowk][nextNode.vertex] > newDist && newDist < distance[nowk - 1][nextNode.vertex]) {
						distance[nowk - 1][nextNode.vertex] = newDist;
						pq.add(new Node(nextNode.vertex, distance[nowk - 1][nextNode.vertex], nowk - 1));
					}
				}
			}
		}
//		for (int i = 0; i < k + 1; i++) {
//			for (int j = 1; j < n + 1; j++) {
//				System.out.print(distance[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("-----");
//
//		for (int i = k; i >= 1; i--) {
//			for (int j = 1; j < n + 1; j++) {
//				if (distance[i][j] < distance[i - 1][j]) {
//					distance[i - 1][j] = distance[i][j];
//				}
//			}
//			System.out.println();
//		}
//		
//		for (int i = 0; i < k + 1; i++) {
//			for (int j = 1; j < n + 1; j++) {
//				System.out.print(distance[i][j] + " ");
//			}
//			System.out.println();
//		}
		System.out.println(distance[0][n]);
		// }
	}
}