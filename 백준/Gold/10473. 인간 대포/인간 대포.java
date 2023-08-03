import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Coordinates {
		double x;
		double y;

		public Coordinates(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static class Node implements Comparable<Node> {
		int vertex;
		double dist;

		public Node(int vertex, double dist) {
			super();
			this.vertex = vertex;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Double.compare(this.dist, o.dist);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		double startx = Double.parseDouble(st.nextToken());
		double starty = Double.parseDouble(st.nextToken());
		st = new StringTokenizer(br.readLine());
		double endx = Double.parseDouble(st.nextToken());
		double endy = Double.parseDouble(st.nextToken());
		st = new StringTokenizer(br.readLine());// number of cannons...number of nodes
		int cannons = Integer.parseInt(st.nextToken());
		double cannonLocation[][] = new double[cannons][2];
		// 0 : x좌표, 1 : y좌표
		int mapCnt = 0;
		Map<Integer, Coordinates> map = new HashMap<>();
		map.put(mapCnt++, new Coordinates(startx, starty));
		map.put(mapCnt++, new Coordinates(endx, endy));
		for (int i = 0; i < cannons; i++) {
			st = new StringTokenizer(br.readLine());
			cannonLocation[i][0] = Double.parseDouble(st.nextToken());
			cannonLocation[i][1] = Double.parseDouble(st.nextToken());
			map.put(mapCnt++, new Coordinates(cannonLocation[i][0], cannonLocation[i][1]));
		}
		// 입력 완료 --------------------------------------------------------------
		List<List<Node>> graph = new ArrayList<>();
		for (int i = 0; i < cannons + 2; i++) {
			graph.add(new ArrayList<>());
		}
		double distance[] = new double[cannons + 2];
		Arrays.fill(distance, Double.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		// A에서 갈 수 있는 모든 대포/B 좌표까지 걸어가는 시간을 간선으로 저장하자
		// 무조건 걸어가야한다
		for (Integer i : map.keySet()) {
			double dx = startx - map.get(i).x;
			double dy = starty - map.get(i).y;
			double calcDist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
			calcDist = calcDist / 5.0;// 걸어가는 시간
			graph.get(0).add(new Node(i, calcDist));
		}
		// 나머지 노드에서 나머지 노드로 가는길
		for (Integer start : map.keySet()) {
			if (start != 0 && start != 1) {// A는 확인 했고 B에서 어디로 가는 길은 필요 없음
				for (Integer end : map.keySet()) {
					if (end != 0) {
						double dx = map.get(start).x - map.get(end).x;
						double dy = map.get(start).y - map.get(end).y;
						double calcDist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
						// 날아가기 + 걷기 혹은 그냥 걸어가기
						// 50이면 2초
						// 50보다 크면 2 + 나머지 거리/5
						// 50보다 작으면 2 + 50-거리/5
						double fly = 0;
						double walk = 0;
						if (calcDist >= 50) {
							fly = 2.0 + (calcDist - 50) / 5.0;
						} else {
							fly = 2.0 + (50 - calcDist) / 5.0;
						}
						walk = calcDist / 5.0;
						double minDist = fly < walk ? fly : walk;
						graph.get(start).add(new Node(end, minDist));

					}
				}
			}
		}
		// 그래프 생성 완료...다익스트라 시작
		pq.add(new Node(0, 0.0));
		distance[0] = 0.0;
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int now = node.vertex;
			double dist = node.dist;
			if (dist > distance[now])
				continue;
			for (Node nextNode : graph.get(now)) {
				double newDist = dist + nextNode.dist;
				if (newDist < distance[nextNode.vertex]) {
					distance[nextNode.vertex] = newDist;
					pq.add(new Node(nextNode.vertex, newDist));
				}
			}
		}

		System.out.println(distance[1]);
	}
}