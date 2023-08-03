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
		boolean canGo;

		public Node(int vertex, int dist, boolean canGo) {
			super();
			this.vertex = vertex;
			this.dist = dist;
			this.canGo = canGo;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.dist, o.dist);
		}

	}

	static PriorityQueue<Node> pq;
	static List<List<Node>> graph;
	static int n;
	static List<List<Integer>> route;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<>();
		graph = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, t, true));
			graph.get(b).add(new Node(a, t, true));

		}

		// 다익스트라를 이용해서 도둑이 이동한 경로를 하나씩 차단 해보자

		int distance[] = new int[n + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		route = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			route.add(new ArrayList<>());
		}

		// 다익스트라로 도둑이 이동할 수 있는 최적의 경로를 먼저 찾자
		pq.add(new Node(1, 0, true));
		distance[1] = 0;
		route.get(1).add(1);

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
					pq.add(new Node(nextNode.vertex, newDist, true));
					// route 갱신
					List<Integer> newRoute = new ArrayList<>(route.get(now));
					newRoute.add(nextNode.vertex);
					route.set(nextNode.vertex, newRoute);
				}
			}
		}
		// 그래프에서 경로를 제외해야함
		// canGo 를 false로 바꾸자
		int answer = 0;
		int bestDist = distance[n];
		if (bestDist == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		List<Integer> bestRoute = route.get(n);
//		System.out.println("bestDistance ; " + bestDist);
//		for(Integer i : bestRoute) {
//			System.out.print(i + "->");
//		}
	//	System.out.println();
		for (int i = 0; i < bestRoute.size() - 1; i++) {

			int start = bestRoute.get(i);
			int end = bestRoute.get(i + 1);

			for (Node cantNode : graph.get(start)) {
				if (cantNode.vertex == end)
					cantNode.canGo = false;
			}
			for (Node cantNode : graph.get(end)) {
				if (cantNode.vertex == start)
					cantNode.canGo = false;
			}

			for (int idx = 0; idx < n + 1; idx++) {
				distance[idx] = Integer.MAX_VALUE;
			}

			pq.add(new Node(1, 0, true));
			distance[1] = 0;
			route.get(1).add(1);

			while (!pq.isEmpty()) {
				Node node = pq.poll();
				int now = node.vertex;
				int dist = node.dist;
				if (dist > distance[now]) {
					continue;
				}
				for (Node nextNode : graph.get(now)) {
					if (nextNode.canGo) {
						int newDist = distance[now] + nextNode.dist;
						if (newDist < distance[nextNode.vertex]) {
							distance[nextNode.vertex] = newDist;
							pq.add(new Node(nextNode.vertex, newDist, true));
						}
					}
				}
			}

			for (Node cantNode : graph.get(start)) {
				if (cantNode.vertex == end)
					cantNode.canGo = true;
			}
			for (Node cantNode : graph.get(end)) {
				if (cantNode.vertex == start)
					cantNode.canGo = true;
			}
			int newBestDistance = distance[n];
			if (newBestDistance == Integer.MAX_VALUE) {
				answer = Integer.MAX_VALUE;
				break;
			} else if (newBestDistance - bestDist > answer) {
				
				answer = newBestDistance - bestDist;
				//System.out.println(i + "번쨰 루프 : " + answer);
			}
		}

		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else
			System.out.println(answer);

	}

}