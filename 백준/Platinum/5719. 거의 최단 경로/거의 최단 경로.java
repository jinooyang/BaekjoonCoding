import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
			return Integer.compare(this.dist, this.dist);
		}

	}

	static class Dist {
		int dist;
		List<Integer> route;

		public Dist(int dist, List<Integer> route) {
			super();
			this.dist = dist;
			this.route = route;
		}

	}

	static class Edge {
		int s;
		int d;

		public Edge(int s, int d) {
			super();
			this.s = s;
			this.d = d;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + d;
			result = prime * result + s;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Edge other = (Edge) obj;
			if (d != other.d)
				return false;
			if (s != other.s)
				return false;
			return true;
		}

	}

	static List<List<Integer>> allRoutes;
	static List<Integer> allDists;
	static List<Integer> allEnds;
	static int D;
	static int dist[];

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		while (true) {
			allRoutes = new ArrayList<>();
			allDists = new ArrayList<>();
			allEnds = new ArrayList<>();
			List<List<Node>> graph = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());// 장소의 수
			int M = Integer.parseInt(st.nextToken());// 도로의 수
			if (N == 0 && M == 0)
				break;
			for (int i = 0; i < N; i++) {
				graph.add(new ArrayList<>());
			}

			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				graph.get(u).add(new Node(v, p));
			}
			int minDist = Integer.MAX_VALUE;
			// Dijkstra init
			Dist[] distance = new Dist[N];
			for (int i = 0; i < N; i++) {
				distance[i] = new Dist(Integer.MAX_VALUE, new ArrayList<>());
			}

			PriorityQueue<Node> pq = new PriorityQueue<>();
			dist = new int[N];
			Arrays.fill(dist, Integer.MAX_VALUE);
			dist[S] = 0;
			pq.add(new Node(S, 0));
			dijkstra3(graph, pq);
//			for(int i : dist) {
//				System.out.print(i + " ");
//			}
//			System.out.println();

			pq = new PriorityQueue<>();
			pq.add(new Node(S, 0));
			distance[S].dist = 0;
			distance[S].route.add(S);
			dijkstra(graph, distance, pq);
			minDist = distance[D].dist;

			if (minDist == Integer.MAX_VALUE) {
				sb.append(-1).append("\n");
				continue;
			}

			// 최단 경로 삭제

			Set<Integer> nodesPassed = new HashSet<>();
			int size = allDists.size();
			for (int i = 0; i < size; i++) {
				if (allEnds.get(i) == D && allDists.get(i) == minDist) {
					for (int x = 0; x < allRoutes.get(i).size(); x++) {
						nodesPassed.add(allRoutes.get(i).get(x));
					}
				}
			}
			for (int i = 0; i < distance[D].route.size(); i++) {
				nodesPassed.add(distance[D].route.get(i));
			}

			Set<Edge> set = new HashSet<>();
			for (int i = 0; i < size; i++) {
				if (nodesPassed.contains(allEnds.get(i))) {
					if (allDists.get(i) == distance[allEnds.get(i)].dist) {// 지워도 되니까 set에 다 넣는다
						for (int x = 0; x < allRoutes.get(i).size() - 1; x++) {
							set.add(new Edge(allRoutes.get(i).get(x), allRoutes.get(i).get(x + 1)));
						}
					}
				}
			}

			for (int i = 0; i < N; i++) {
				if (nodesPassed.contains(i)) {
					for (int j = 0; j < distance[i].route.size() - 1; j++) {
						set.add(new Edge(distance[i].route.get(j), distance[i].route.get(j + 1)));
					}
				}
			}

			// 거의 최단 경로 구하기

			int distance2[] = new int[N];
			Arrays.fill(distance2, Integer.MAX_VALUE);
			pq = new PriorityQueue<>();
			pq.add(new Node(S, 0));
			distance2[S] = 0;
			dijkstra2(graph, pq, distance2, set);
			// 정답 기록
			if (distance2[D] == Integer.MAX_VALUE) {
				sb.append(-1).append("\n");
			} else
				sb.append(distance2[D]).append("\n");
		}

		System.out.println(sb);
	}

	private static void dijkstra3(List<List<Node>> graph, PriorityQueue<Node> pq) {
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (now.dist > dist[now.to])
				continue;
			for (Node nextNode : graph.get(now.to)) {
				int newDist = now.dist + nextNode.dist;
				if (newDist < dist[nextNode.to]) {
					dist[nextNode.to] = newDist;
					pq.add(new Node(nextNode.to, newDist));
				}
			}
		}
	}

	private static void dijkstra2(List<List<Node>> graph, PriorityQueue<Node> pq, int[] distance2, Set<Edge> set) {
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (now.dist > distance2[now.to])
				continue;
			for (Node nextNode : graph.get(now.to)) {
				if (set.contains(new Edge(now.to, nextNode.to)))
					continue;
				int newDist = now.dist + nextNode.dist;
				if (newDist < distance2[nextNode.to]) {
					distance2[nextNode.to] = newDist;
					pq.add(new Node(nextNode.to, newDist));
				}
			}
		}
	}

	private static void dijkstra(List<List<Node>> graph, Dist[] distance, PriorityQueue<Node> pq) {
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (now.dist > distance[now.to].dist)
				continue;
			for (Node nextNode : graph.get(now.to)) {
				int newDist = now.dist + nextNode.dist;

				if (newDist < distance[nextNode.to].dist) {
					distance[nextNode.to].dist = newDist;
					distance[nextNode.to].route.clear();
					for (int i = 0; i < distance[now.to].route.size(); i++) {
						distance[nextNode.to].route.add(distance[now.to].route.get(i));
					}
					distance[nextNode.to].route.add(nextNode.to);
					pq.add(new Node(nextNode.to, newDist));

				}
				// 도착지점까지의 경로 저장한다
				if (newDist == distance[nextNode.to].dist && newDist == dist[nextNode.to]) {

//					distance[nextNode.to].dist = newDist;
//					distance[nextNode.to].route.clear();
//					for (int i = 0; i < distance[now.to].route.size(); i++) {
//						distance[nextNode.to].route.add(distance[now.to].route.get(i));
//					}
//					distance[nextNode.to].route.add(nextNode.to);
//					pq.add(new Node(nextNode.to, newDist));
					List<Integer> newList = new ArrayList<>();
					for (int i = 0; i < distance[now.to].route.size(); i++) {
						newList.add(distance[now.to].route.get(i));
					}
					newList.add(nextNode.to);
					allRoutes.add(newList);
					allDists.add(newDist);
					allEnds.add(nextNode.to);
				}
			}
		}
	}
}