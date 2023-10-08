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
		int to;
		long dist;

		public Node(int to, long dist) {
			super();
			this.to = to;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.dist, o.dist);
		}

	}

	static class Wolf implements Comparable<Wolf> {
		int to;
		long dist;
		int two;

		public Wolf(int to, long dist, int two) {
			super();
			this.to = to;
			this.dist = dist;
			this.two = two;
		}

		@Override
		public int compareTo(Wolf o) {
			return Long.compare(this.dist, o.dist);
		}

	}

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		List<List<Node>> graph = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Node(v, 2 * w));
			graph.get(v).add(new Node(u, 2 * w));
		}
		// fox
		PriorityQueue<Node> pq = new PriorityQueue<>();
		long distance[] = new long[n + 1];
		Arrays.fill(distance, Long.MAX_VALUE);
		pq.add(new Node(1, 0));
		distance[1] = 0;
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (now.dist > distance[now.to])
				continue;
			for (Node next : graph.get(now.to)) {
				long newDist = now.dist + next.dist;
				if (newDist < distance[next.to]) {
					distance[next.to] = newDist;
					pq.add(new Node(next.to, newDist));
				}
			}
		}

		PriorityQueue<Wolf> wolfpq = new PriorityQueue<>();
		long distance_wolf[][] = new long[2][n + 1];
		Arrays.fill(distance_wolf[0], Long.MAX_VALUE);
		Arrays.fill(distance_wolf[1], Long.MAX_VALUE);

		wolfpq.add(new Wolf(1, 0, 1));

		distance_wolf[0][1] = 0;
	//	distance_wolf[1][1] = 0;
		while (!wolfpq.isEmpty()) {
			Wolf now = wolfpq.poll();
			int two = now.two % 2;
			if (now.dist > distance_wolf[(two + 1) % 2][now.to])
				continue;
			for (Node next : graph.get(now.to)) {

				long newDist = now.dist;
				if ((now.two % 2) == 0) {
					newDist += next.dist * 2;
				} else {
					newDist += next.dist / 2;
				}
				if (newDist < distance_wolf[two][next.to]) {
					distance_wolf[two][next.to] = newDist;
					int nextTo = next.to;
					int nextTwo = now.two + 1;
					wolfpq.add(new Wolf(nextTo, newDist, nextTwo));
				}
			}
		}

		int answer = 0;

		for (int i = 1; i <= n; i++) {
			if (distance[i] < Math.min(distance_wolf[0][i], distance_wolf[1][i]))
				answer++;
		}
		System.out.println(answer);

	}
}