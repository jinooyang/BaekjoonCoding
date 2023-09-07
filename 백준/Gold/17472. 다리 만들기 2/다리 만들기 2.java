import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int ary[][];
	static int deli[] = { 0, 0, 1, -1 };// 우좌 하상
	static int delj[] = { 1, -1, 0, 0 };

	static class Node {
		int i;
		int j;
		int dir;
		int dist;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

		public Node(int i, int j, int dir, int dist) {
			super();
			this.i = i;
			this.j = j;
			this.dir = dir;
			this.dist = dist;
		}
	}

	static int cnt;
	static int n;
	static int m;
	static int graph[][];
	static int vertexCnt;
	static Map<Integer, List<Node>> islandNodes;
	static int parent[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		cnt = 0;
		ary = new int[n][m];
		islandNodes = new HashMap<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
				if (ary[i][j] == 1)
					ary[i][j] = -1;
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (ary[i][j] == -1) {
					colorIslands(i, j);
				}

			}
		}
//		printary();
//		System.out.println("cnt : " + cnt);
		graph = new int[cnt * cnt][3];
		vertexCnt = 0;
		for (int i = 0; i < cnt * cnt; i++) {
			Arrays.fill(graph[i], Integer.MAX_VALUE);
		}

		Set<Integer> checkedIsland = new HashSet<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (ary[i][j] != 0) {
					if (!checkedIsland.contains(ary[i][j])) {
						checkedIsland.add(ary[i][j]);

						getDist(ary[i][j]);// 현재 섬에서 우,하로 뻗어가며 가장 가까운 섬의 거리를 구한다
						// 해당 정보를 graph에 저장한다
					}
				}
			}
		}

//		for (int i = 0; i < vertexCnt; i++) {
//			System.out.println(graph[i][0] + " " + graph[i][1] + " " + graph[i][2]);
//		}
		parent = new int[cnt + 1];
		for (int i = 1; i <= cnt; i++) {
			parent[i] = i;
		}
		Arrays.sort(graph, (o1, o2) -> Integer.compare(o1[2], o2[2]));
		int answer = 0;
		for (int i = 0; i < vertexCnt; i++) {
			int u = graph[i][0];
			int v = graph[i][1];
			int w = graph[i][2];
			if (find(u) != find(v)) {
//				System.out.println(u +" "+v);
				answer += w;
				union(u, v);
			}
		}

		boolean isAnswer = true;
		int p = find(1);
		for (int i = 1; i <= cnt; i++) {
			if (p != find(i))
				isAnswer = false;
		}

		if (!isAnswer || answer == 0)
			System.out.println(-1);
		else
			System.out.println(answer);

	}

	private static void union(int x, int y) {
		int a = find(x);
		int b = find(y);
		if (a > b) {
			parent[a] = b;
		} else
			parent[b] = a;
	}

	private static int find(int x) {
		if (x == parent[x])
			return x;
		else
			return parent[x] = find(parent[x]);

	}

	private static void getDist(int x) {

		Queue<Node> q = new ArrayDeque<>();
		int dist[] = new int[cnt + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		for (int i = 0; i < islandNodes.get(x).size(); i++) {
			Node node = islandNodes.get(x).get(i);
			q.add(new Node(node.i, node.j, -1, 0));
		}

		while (!q.isEmpty()) {
			Node now = q.poll();
			int is = ary[now.i][now.j];
//			System.out.println(now.i +" "+ now.j);
			if (is != 0 && is != x) {
				if (now.dist - 1 < dist[is] && now.dist - 1 > 1) {
					dist[is] = now.dist - 1;
				}
				continue;
			}
			if (now.dir == -1) {
				// 우
				int di = deli[0] + now.i;
				int dj = delj[0] + now.j;

				if (di >= 0 && dj >= 0 && di < n && dj < m) {
					if (ary[di][dj] != x) {
						q.add(new Node(di, dj, 0, now.dist + 1));
					}
				}
				// 하
				di = deli[2] + now.i;
				dj = delj[2] + now.j;

				if (di >= 0 && dj >= 0 && di < n && dj < m) {
					if (ary[di][dj] != x) {
						q.add(new Node(di, dj, 2, now.dist + 1));
					}
				}
			} else {
				// 원래 가던 방향
				int di = deli[now.dir] + now.i;
				int dj = delj[now.dir] + now.j;
				if (di >= 0 && dj >= 0 && di < n && dj < m) {
					if (ary[di][dj] != x) {
						q.add(new Node(di, dj, now.dir, now.dist + 1));
					}
				}
			}

		}
		// 거리를 저장하자
		for (int i = 1; i <= cnt; i++) {
			if (dist[i] < Integer.MAX_VALUE) {
				graph[vertexCnt][0] = x;
				graph[vertexCnt][1] = i;
				graph[vertexCnt][2] = dist[i];
				vertexCnt++;
			}

		}

//		System.out.print(x + "에서 시작 : ");
//		for (int i = 1; i <= cnt; i++) {
//			System.out.print(dist[i] + " ");
//		}
//		System.out.println();

	}

	private static void printary() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(ary[i][j] + " ");
			}
			System.out.println();
		}
	}

	// 섬을 모두 같은 색으로 칠한다
	private static void colorIslands(int x, int y) {

		cnt++;
		islandNodes.put(cnt, new ArrayList<>());

		Queue<Node> q = new ArrayDeque<>();
		ary[x][y] = cnt;
		islandNodes.get(cnt).add(new Node(x, y));
		for (int idx = 0; idx < 4; idx++) {
			int di = x + deli[idx];
			int dj = y + delj[idx];
			if (di >= 0 && dj >= 0 && di < n && dj < m && ary[di][dj] == -1) {
				q.add(new Node(di, dj));
				ary[di][dj] = cnt;
				islandNodes.get(cnt).add(new Node(di, dj));
			}
		}

		while (!q.isEmpty()) {
			Node now = q.poll();
			for (int idx = 0; idx < 4; idx++) {
				int di = now.i + deli[idx];
				int dj = now.j + delj[idx];
				if (di >= 0 && dj >= 0 && di < n && dj < m && ary[di][dj] == -1) {
					ary[di][dj] = cnt;
					islandNodes.get(cnt).add(new Node(di, dj));
					q.add(new Node(di, dj));
				}
			}
		}

	}
}