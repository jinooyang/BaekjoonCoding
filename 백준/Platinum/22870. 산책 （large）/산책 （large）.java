import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int to;
		int dist;

		public Node(int to, int dist) {
			super();
			this.to = to;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			if (this.dist == o.dist)
				return Integer.compare(this.to, o.to);
			else
				return Integer.compare(this.dist, o.dist);
		}

	}

	public static void main(String[] args) throws IOException {
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
			graph.get(u).add(new Node(v, w));
			graph.get(v).add(new Node(u, w));
		}

		for (int i = 0; i < n + 1; i++) {
			Collections.sort(graph.get(i));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		int distance[] = new int[n + 1];

		List<Integer> route[] = new List[n + 1];
		for (int i = 0; i < n + 1; i++) {
			route[i] = new ArrayList<>();
			distance[i] = Integer.MAX_VALUE;
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		route[start].add(start);
		distance[start] = 0;
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			int to = now.to;
			int dist = now.dist;
			if (dist > distance[to])
				continue;
			for (Node next : graph.get(to)) {
				int newDist = next.dist + dist;
				if (newDist < distance[next.to]) {
					distance[next.to] = newDist;
					route[next.to] = new ArrayList<>(route[to]);
					route[next.to].add(next.to);
					pq.add(new Node(next.to, newDist));
				}
				if (newDist == distance[next.to]) {
					boolean change = false;
					for (int i = 0; i < route[next.to].size(); i++) {
						int x = route[next.to].get(i);
						int y = 0;
						if (i < route[to].size()) {
							y = route[to].get(i);
						}
						if (i == route[to].size()) {
							y = next.to;
						}
						if (x > y) {
							change = true;
							break;
						}
						if (x == y)
							continue;
						if (x < y)
							break;
					}
					if (change) {
						route[next.to] = new ArrayList<>(route[to]);
						route[next.to].add(next.to);
						pq.add(new Node(next.to, newDist));
					}

//					StringBuilder orgRoute = new StringBuilder();
//					StringBuilder newRoute = new StringBuilder();
//					for (int i = 0; i < route[next.to].size(); i++) {
//						orgRoute.append(route[next.to].get(i));
//					}
//					for (int i = 0; i < route[to].size(); i++) {
//						newRoute.append(route[to].get(i));
//					}
//					newRoute.append(next.to);
//					String or = orgRoute.toString();
//					String nr = newRoute.toString();
//					if (or.compareTo(nr) > 0) {
//						route[next.to] = new ArrayList<>(route[to]);
//						route[next.to].add(next.to);
//						pq.add(new Node(next.to, newDist));
//					}
				}
			}
		}
		int answer = distance[end];
		List<Integer> deleteList = route[end];
		Set<Integer> cantGo = new HashSet<>();
		for (int i = 1; i < deleteList.size() - 1; i++) {
			cantGo.add(deleteList.get(i));
		}

		pq = new PriorityQueue<>();
		distance = new int[n + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		pq.add(new Node(end, 0));
		distance[end] = 0;
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (now.dist > distance[now.to])
				continue;
			for (Node next : graph.get(now.to)) {
				if (cantGo.contains(next.to))
					continue;
				int newDist = next.dist + now.dist;
				if (newDist < distance[next.to]) {
					distance[next.to] = newDist;
					pq.add(new Node(next.to, newDist));
				}
			}
		}
		answer += distance[start];
		System.out.println(answer);
	}
}