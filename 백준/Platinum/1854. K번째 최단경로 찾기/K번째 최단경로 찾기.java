import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Node implements Comparable<Node>{
		int to;
		int dist;

		public Node(int to, int dist) {
			super();
			this.to = to;
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
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		List<List<Node>> graph = new ArrayList<>();
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<>());
		}

		PriorityQueue<Integer> dist[] = new PriorityQueue[N + 1];
		for (int i = 0; i < N + 1; i++) {
			dist[i] = new PriorityQueue<>(Collections.reverseOrder());
			for (int j = 0; j < K; j++) {
				dist[i].add(Integer.MAX_VALUE);
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, c));
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));
		dist[1].poll();
		dist[1].add(0);
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (now.dist > dist[now.to].peek())
				continue;
			for (Node nextNode : graph.get(now.to)) {
				int newDist = now.dist + nextNode.dist;
				if (newDist < dist[nextNode.to].peek()) {
					dist[nextNode.to].poll();
					dist[nextNode.to].add(newDist);
					pq.add(new Node(nextNode.to, newDist));
				}
			}
		}
		StringBuilder sb = new StringBuilder();
//		for (int i = 1; i < N + 1; i++) {
//		System.out.println(dist[i].toString());
//		}
		for (int i = 1; i < N + 1; i++) {
			if (dist[i].peek() == Integer.MAX_VALUE) {
				sb.append(-1).append("\n");
			}
			else sb.append(dist[i].peek()).append("\n");
		}
		System.out.println(sb.toString());
	}
}