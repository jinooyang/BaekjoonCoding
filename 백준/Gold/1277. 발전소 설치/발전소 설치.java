import java.io.BufferedReader;
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

	// 그래프 노드
	static class Node implements Comparable<Node> {
		int nodeNum;

		double dist;

		public Node(int nodeNum, double dist) {
			super();
			this.nodeNum = nodeNum;

			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {

			return Double.compare(this.dist, o.dist);
		}

		@Override
		public String toString() {
			return "Node [nodeNum=" + nodeNum + ", dist=" + dist + "]";
		}

	}

	// 발전소 위치
	static class Factory {
		int i;
		int j;

		public Factory(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

	}

	static List<Factory> factories;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());

		// 최대 길이
		double M = Double.parseDouble(st.nextToken());

		factories = new ArrayList<>();
		factories.add(new Factory(0, 0));
		// 발전소 위치 정보를 저장한다
		for (int k = 1; k <= N; k++) {
			st = new StringTokenizer(br.readLine());
			// 앞에 좌표가 가로, 뒤에 좌표가 세로
			int j = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken());

			factories.add(new Factory(i, j));
		}

		// 그래프
		List<List<Node>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		// 연결되어있는 발전소인지 확인할 수 있음
		Set<Integer> connectedSet = new HashSet<>();
		boolean graphConn[][] = new boolean[N + 1][N + 1];
		// 그래프 입력받는다
		for (int k = 0; k < W; k++) {
			st = new StringTokenizer(br.readLine());
			int fac1 = Integer.parseInt(st.nextToken());
			connectedSet.add(fac1);
			int fac2 = Integer.parseInt(st.nextToken());
			connectedSet.add(fac2);

			// 두 발전소의 거리
			double dist = getDist(fac1, fac2);
			graph.get(fac1).add(new Node(fac2, dist));
			graph.get(fac2).add(new Node(fac1, dist));
			graphConn[fac1][fac2] = true;
			graphConn[fac2][fac1] = true;
		}

		for (int i = 1; i < N; i++) {
//			if (connectedSet.contains(i))
//				continue;
			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;
				// System.out.println(i + " " + j);
				graph.get(i).add(new Node(j, getDist(i, j)));
				graph.get(j).add(new Node(i, getDist(i, j)));
			}
		}

//		for (int i = 0; i < N + 1; i++) {
//
//		}

		// 시작점에서 끝점까지 다익스트라. 근데 connected점사이를 지날때는 거리가 0으로 계산한다

		double distance[] = new double[N + 1];
		Arrays.fill(distance, Double.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue<>();

		distance[1] = 0;
		pq.add(new Node(1, 0));
		while (!pq.isEmpty()) {
			Node now = pq.poll();

			if (now.dist > distance[now.nodeNum])
				continue;
			for (Node nextNode : graph.get(now.nodeNum)) {

				double distToNextNode = nextNode.dist;
				// 둘이 이미 연결되어있으면 0으로 바꿔주자
				// 연결되어있는지 확인하기
				if (graphConn[now.nodeNum][nextNode.nodeNum] == true) {
					distToNextNode = 0;
				}
//				if (distToNextNode > M)
//					continue;
				double newDist = distToNextNode + now.dist;
				if (newDist < distance[nextNode.nodeNum]) {
					distance[nextNode.nodeNum] = newDist;
					pq.add(new Node(nextNode.nodeNum, newDist));
				}
			}
		}

		System.out.println((long) (distance[N] * 1000));
	}

	// get Dist between factories
	public static double getDist(int a, int b) {

		double ai = factories.get(a).i;
		double aj = factories.get(a).j;

		double bi = factories.get(b).i;
		double bj = factories.get(b).j;

		double i = Math.abs(ai - bi);
		double j = Math.abs(aj - bj);

		return Math.sqrt(i * i + j * j);
	}
}