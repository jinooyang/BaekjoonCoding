import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static class Node implements Comparable<Node> {

		int vertex;
		int dist;

		public Node(int vertex, int dist) {
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
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		List<List<Node>> map = new ArrayList<>();

		for (int i = 0; i < N + 1; i++) {
			map.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {// edge
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			map.get(a).add(new Node(b, c));
			map.get(b).add(new Node(a, c));
		}

		int v1 = 0;
		int v2 = 0;
		st = new StringTokenizer(br.readLine());

		v1 = Integer.parseInt(st.nextToken());// 2
		v2 = Integer.parseInt(st.nextToken());// 3

		int distOne[] = dijkstra(N, map, 1);
		// printAry(N, distOne);
		int distV1[] = dijkstra(N, map, v1);
		// printAry(N, distV1);
		int distV2[] = dijkstra(N, map, v2);
		// printAry(N, distV2);

		int firstOption = distOne[v1] + distV2[N] + distV1[v2];
		int secondOption = distOne[v2] + distV1[N] + distV2[v1];

		if (firstOption >= 100000000 && secondOption >= 100000000) {
			System.out.println(-1);
		} else
			System.out.println(firstOption < secondOption ? firstOption : secondOption);

	}

	private static void printAry(int N, int[] distOne) {
		for (int i = 1; i < N + 1; i++) {
			System.out.print(distOne[i] + " ");
		}
		System.out.println();
	}

	private static int[] dijkstra(int N, List<List<Node>> map, int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int distance[] = new int[N + 1];
		Arrays.fill(distance, 100000000);
		pq.add(new Node(start, 0));
		distance[start] = 0;
		while (!pq.isEmpty()) {
			Node n = pq.poll();
			int now = n.vertex;
			int nowDist = n.dist;
//			System.err.println(nowDist);
//			if (nowDist >= distance[now])
//				continue;
			for (Node nextNode : map.get(now)) {
				// System.err.println("a");
				int newDist = nextNode.dist + distance[now];
				if (newDist < distance[nextNode.vertex]) {
					distance[nextNode.vertex] = newDist;
					pq.add(new Node(nextNode.vertex, newDist));
				}
			}
		}
		return distance;
	}
}