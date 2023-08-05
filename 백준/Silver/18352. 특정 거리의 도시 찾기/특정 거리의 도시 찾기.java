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
		int v;
		int d;

		public Node(int v, int d) {
			super();
			this.v = v;
			this.d = d;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.d, o.d);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		List<List<Node>> map = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			map.add(new ArrayList<>());
		}
		int distance[] = new int[n + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			map.get(u).add(new Node(v, 1));
		}
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		distance[start] = 0;
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int now = node.v;
			int dist = node.d;
			if (dist > distance[now])
				continue;
			for (Node nextNode : map.get(now)) {
				int newDist = distance[now] + nextNode.d;
				if (newDist < distance[nextNode.v]) {
					distance[nextNode.v] = newDist;
					pq.add(new Node(nextNode.v, newDist));
				}
			}
		}

		int answer = 0;
		for (int i = 1; i < n + 1; i++) {
			if (distance[i] == k) {
				answer++;
				System.out.println(i);
			}
		}

		if (answer == 0) {
			answer = -1;
			System.out.println(answer);
		}
	}
}