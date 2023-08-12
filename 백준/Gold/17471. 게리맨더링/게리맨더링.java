import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int population[];
	static int graph[][];
	static Set<Integer> set = new HashSet<>();
	static int n;
	static int minDiff = Integer.MAX_VALUE;
	static int edges = 0;
	static int totalPopulation = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		population = new int[n + 1];
		graph = new int[n*n][2];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			totalPopulation += population[i];
		}
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				int v = Integer.parseInt(st.nextToken());
				graph[edges][0] = i;
				graph[edges][1] = v;
				edges++;
			}
		}
		set.add(1);
		comb(1, 1);
		if (minDiff == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(minDiff);

	}

	private static void comb(int cnt, int before) {
		if (cnt == n)
			return;

		calcDiff();

		for (int i = before + 1; i <= n; i++) {
			set.add(i);
			comb(cnt + 1, i);
			set.remove(i);
		}
	}

	// set에 있는 도시들이 연결되어 있는지 확인한다.
	// 인구수 차이를 구한다
	// 크루스칼 이용...도로간 거리는 생각안한다
	private static void calcDiff() {
//		System.out.print("set contains : ");
//		for (Integer i : set) {
//			System.out.print(i + " ");
//		}
//		System.out.println();
		int parent[] = new int[n + 1];
		for (int i = 0; i < n + 1; i++) {
			parent[i] = i;
		}
		// 선택한 구역을 연결하자
		for (int i = 0; i < edges; i++) {
			int x = graph[i][0];
			int y = graph[i][1];
			if (set.contains(x) && set.contains(y)) {// A구역이면
				if (find(x, parent) != find(y, parent)) {
					union(x, y, parent);
				}

			}

			if (!set.contains(x) && !set.contains(y)) {// B구역이면
				if (find(x, parent) != find(y, parent)) {
					union(x, y, parent);
				}
			}
		}
		boolean isConnected = true;
		int APopultaion = 0;
		int BPopultaion = 0;
		int otherParent = -1;
		for (int i = 1; i < n + 1; i++) {
			if (set.contains(i)) {// A구역...set에 A구역 정보가 있음
				if (find(i, parent) != parent[1]) {
					isConnected = false;
					break;
				}
				APopultaion += population[i];
			} else {// B구역...set에 없음
				if (otherParent == -1)
					otherParent = find(i, parent);
				else {
					if (find(i, parent) != otherParent) {
						isConnected = false;
						break;
					}
				}
				BPopultaion += population[i];
			}
		}
		if (isConnected) {// 나머지가 연결되었는지도 확인해야함
			// System.out.println("연결됨");
			int diff = Math.abs(APopultaion - BPopultaion);
			if (diff < minDiff) {
				// System.out.println("갱신됨 : " + diff);
				minDiff = diff;
			}
		}
	}

	private static void union(int x, int y, int[] parent) {
		int a = parent[x];
		int b = parent[y];
		if (a > b)
			parent[a] = b;
		else
			parent[b] = a;
	}

	private static int find(int x, int[] parent) {
		if (parent[x] == x)
			return x;
		else
			return find(parent[x], parent);
	}

}