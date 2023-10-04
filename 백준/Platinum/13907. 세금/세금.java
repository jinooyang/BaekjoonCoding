import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int to;
		int dist;
		int cnt;

		public Node(int to, int dist, int cnt) {
			super();
			this.to = to;
			this.dist = dist;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {

			return Integer.compare(this.dist, o.dist);
		}

	}

	static class NodeB {
		int dist;
		int cnt;

		public NodeB(int dist, int cnt) {
			super();
			this.dist = dist;
			this.cnt = cnt;
		}

	}

	static List<List<Node>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n + 1; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, c, 0));
			graph.get(b).add(new Node(a, c, 0));
		}

		int distance[][] = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++)
			Arrays.fill(distance[i], Integer.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(s, 0, 0));
		distance[s][0] = 0;

		int md = Integer.MAX_VALUE;
		int mcnt = Integer.MAX_VALUE;
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int to = node.to;
			int dist = node.dist;
			int cnt = node.cnt;
			if (dist > distance[to][cnt])
				continue;
			if (cnt == n)
				continue;
			if (to == d) {
				if (cnt > mcnt && dist > md)
					continue;
				if (dist < md) {
					md = dist;
					mcnt = cnt;
				}
			}
			if (dist > md && cnt > mcnt)
				continue;
			for (Node next : graph.get(to)) {
				int newDist = dist + next.dist;
				if (newDist < distance[next.to][cnt + 1]) {
					distance[next.to][cnt + 1] = newDist;
					pq.add(new Node(next.to, newDist, cnt + 1));
				}
			}
		}

		List<NodeB> list = new ArrayList<>();
		int min = Integer.MAX_VALUE;
		for (int i = 0; i <= n; i++) {
			int x = distance[d][i];
			if (x < min) {
				list.add(new NodeB(x, i));
				min = x;
			}
		}
		sb.append(min).append("\n");

		int sum = 0;
		int end = list.size() - 1;
		// System.out.println("end : " + end);
		for (int i = 0; i < k; i++) {
			int x = Integer.parseInt(br.readLine());
			sum += x;
			int minDist = Integer.MAX_VALUE;
			int nextEnd = 0;
			for (int j = 0; j <= end; j++) {
				int calcDist = list.get(j).dist + sum * list.get(j).cnt;
				// System.out.println(sum+ " : "+i + ", " +j +" : "+calcDist);
				if (minDist > calcDist) {
					minDist = calcDist;
					nextEnd = j;
				}
			}
			end = nextEnd;
			sb.append(minDist).append("\n");

		}
		System.out.println(sb);
		// printDist(n, n, distance);
	}

	private static void printDist(int n, int m, int[][] distance) {
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				if (distance[i][j] == Integer.MAX_VALUE) {

					System.out.print(-1 + " ");
				} else {

					System.out.print(distance[i][j] + " ");
				}
			}
			System.out.println();
		}
	}
}
