import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int i;
		int j;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + i;
			result = prime * result + j;
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
			Node other = (Node) obj;
			if (i != other.i)
				return false;
			if (j != other.j)
				return false;
			return true;
		}

	}

	static Map<Node, Integer> chicken = new HashMap<>();
	static Map<Node, Integer> house = new HashMap<>();
	static int m;
	static int minDist = Integer.MAX_VALUE;
	static Set<Integer> set = new HashSet<>();
	static int graph[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int nodeNumber = 0;

		// 입력을 받으면서 각 치킨집, 집의 위치와 노드 번호를 맵에 저장한다
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 1)
					house.put(new Node(i, j), nodeNumber++);
				if (temp == 2)
					chicken.put(new Node(i, j), nodeNumber++);
			}
		}
		graph = new int[chicken.size() * house.size()][3];
		int idx = 0;
		// 각 치킨 집에서 집까지 거리를 그래프에 저장한다
		List<Integer> chickenlist = new ArrayList<>();
		for (Node nodeChicken : chicken.keySet()) {
			chickenlist.add(chicken.get(nodeChicken));
			for (Node nodeHouse : house.keySet()) {
				graph[idx][0] = chicken.get(nodeChicken);// 치킨 집 번호
				graph[idx][1] = house.get(nodeHouse);// 집 번호
				graph[idx][2] = Math.abs(nodeChicken.i - nodeHouse.i) + Math.abs(nodeChicken.j - nodeHouse.j);
				idx++;
			}
		}
		// 모든 치킨 집 중 m개를 골라서 크루스칼 알고리즘을 반복적으로 적용한다
		// 치킨 집들은 같은 parent를 갖는다
		kruskal(0, -1, chickenlist, 0);

		System.out.println(minDist);
	}

	private static void kruskal(int cnt, int before, List<Integer> chickenlist, int start) {
		if (cnt == m) {
			// kruskal
//			for (Integer i : set) {
//				System.out.print(i + " ");
//			}
//			System.out.println();
			int parent[] = new int[house.size() + chicken.size()];
			for (int i = 0; i < parent.length; i++) {

				if (set.contains(i))
					parent[i] = start;
				else
					parent[i] = i;
			}
//			for (int i = 0; i < parent.length; i++) {
//				System.out.print(parent[i] + " ");
//			}
//			System.out.println();
			Arrays.sort(graph, (o1, o2) -> Integer.compare(o1[2], o2[2]));
			int dist = 0;
			for (int i = 0; i < graph.length; i++) {
				if (set.contains(graph[i][0])) {
					if (find(graph[i][0], parent) != find(graph[i][1], parent)) {
						//System.out.println("chicken : " + graph[i][0] + ", to  : " + graph[i][1]);
						dist += graph[i][2];
						union(graph[i][0], graph[i][1], parent);
					}
				}
			}
//			for (int i = 0; i < parent.length; i++) {
//				System.out.print(parent[i] + " ");
//			}
//			System.out.println();

			if (dist < minDist)
				minDist = dist;

			return;
		}
		for (int i = before + 1; i < chickenlist.size(); i++) {

			set.add(chickenlist.get(i));
			if (cnt == 0) {

				start = chickenlist.get(i);
			}
			kruskal(cnt + 1, i, chickenlist, start);
			set.remove(chickenlist.get(i));

		}
	}

	private static void union(int chickenidx, int houseidx, int[] parent) {
		int a = find(chickenidx, parent);
		int b = find(houseidx, parent);

		parent[b] = a;

	}

	private static int find(int i, int[] parent) {
		if (parent[i] == i)
			return i;
		else
			return find(parent[i], parent);
	}
}