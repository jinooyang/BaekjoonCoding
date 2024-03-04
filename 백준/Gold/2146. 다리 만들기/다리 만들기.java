import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static class Node {
		int i;
		int j;

		public Node(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	static int[] deli = {0, 0, 1, -1};
	static int[] delj = {1, -1, 0, 0};
	static int n;
	static boolean[][] visited;
	static int[][] ary;

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		ary = new int[n][n];
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean visitedIsland[][] = new boolean[n][n];
		List<List<Node>> islandList = new ArrayList<>();

		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (ary[i][j] == 1 && !visitedIsland[i][j]) {
					visitedIsland[i][j] = true;
					islandList.add(new ArrayList<>());
					islandList.get(islandList.size() - 1).add(new Node(i, j));
					//bfs를 통해서 섬의 면적을 isLandList에 저장한다
					Queue<Node> q = new ArrayDeque<>();
					q.add(new Node(i, j));
					while (!q.isEmpty()) {
						Node now = q.poll();
						for (int idx = 0; idx < 4; idx++) {
							int di = deli[idx] + now.i;
							int dj = delj[idx] + now.j;
							if (di >= 0 && dj >= 0 && di < n && dj < n && ary[di][dj] == 1 && !visitedIsland[di][dj]) {
								visitedIsland[di][dj] = true;
								islandList.get(islandList.size() - 1).add(new Node(di, dj));
								q.add(new Node(di, dj));
							}
						}
					}

				}
			}
		}
		// printIslandList(islandList);
		//각 섬에서 bfs한다
		for (int i = 0; i < islandList.size(); i++) {
			List<Node> now = islandList.get(i);
			int res = bfs(now);
			answer = Math.min(answer, res);
		}
		System.out.println(answer);
	}

	private static int bfs(List<Node> nowList) {
		// System.out.println("!");
		//하나의 섬에서 다른 섬을 찾을때까지 BFS
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			Arrays.fill(visited[i], false);
		}
		Queue<Node> q = new ArrayDeque<>();
		Queue<Integer> qCnt = new ArrayDeque<>();
		for (int i = 0; i < nowList.size(); i++) {
			Node nowNode = nowList.get(i);
			q.add(nowNode);
			qCnt.add(0);
			visited[nowNode.i][nowNode.j] = true;
		}

		while (!q.isEmpty()) {
			Node now = q.poll();
			int cnt = qCnt.poll();
			for (int idx = 0; idx < 4; idx++) {
				int di = deli[idx] + now.i;
				int dj = delj[idx] + now.j;
				if (di >= 0 && dj >= 0 && di < n && dj < n && !visited[di][dj]) {
					q.add(new Node(di, dj));
					qCnt.add(cnt + 1);
					visited[di][dj] = true;
					//이어서 하기
					if (ary[di][dj] == 1) {
						result = cnt;
						return result;
					}
				}
			}
		}
		return result;

	}

	private static void printIslandList(List<List<Node>> islandList) {
		for (int i = 0; i < islandList.size(); i++) {
			for (int j = 0; j < islandList.get(i).size(); j++) {
				System.out.print("[" + islandList.get(i).get(j).i + " " + islandList.get(i).get(j).j + "]");
			}
			System.out.println();
		}
	}

}
