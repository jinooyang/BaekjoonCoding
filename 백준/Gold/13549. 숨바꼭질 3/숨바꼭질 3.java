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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		List<List<Node>> graph = new ArrayList<>();
		for (int i = 0; i < 100001; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < 100001; i++) {
			int i1 = i - 1;
			int i2 = i + 1;
			int i3 = i * 2;
			if (i1 >= 0) {
				graph.get(i).add(new Node(i1, 1));
			}
			if (i2 < 100001) {
				graph.get(i).add(new Node(i2, 1));
			}
			if (i != i3 && i3 < 100001) {
				graph.get(i).add(new Node(i3, 0));
			}

		}
		int distance[] = new int[100001];
		Arrays.fill(distance, Integer.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		distance[start] = 0;
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int dist = node.dist;
			int now = node.vertex;
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
		System.out.println(distance[end]);
	}
}