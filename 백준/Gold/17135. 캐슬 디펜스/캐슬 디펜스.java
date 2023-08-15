import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int n;// 행의 수
	static int m;// 열의 수
	static int d;// 궁수의 공격 거리 제한 D
	static int ary[][];
	static int maxAnswer = 0;

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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		ary = new int[n + 1][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		List<Integer> archerLocation = new ArrayList<>();
		getArcherCombo(archerLocation, 0, -1);
		System.out.println(maxAnswer);
	}

	private static void getArcherCombo(List<Integer> archerLocation, int archerCount, int before) {
		if (archerCount == 3) {
			int result = playGame(archerLocation);
			if (result > maxAnswer)
				maxAnswer = result;
			return;
		}
		// 궁수의 좌표는 [n][i]
		for (int i = before + 1; i < m; i++) {
			archerLocation.add(i);
			getArcherCombo(archerLocation, archerCount + 1, i);
			archerLocation.remove(archerLocation.size() - 1);
		}
	}

	private static int playGame(List<Integer> archerLocation) {
		int map[][] = new int[n + 1][m];
		int result = 0;
		// 맵 복사한다
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = ary[i][j];
			}
		}

		for (int round = 0; round < n; round++) {
			// 궁수 공격
			Set<Node> set = new HashSet<>();
			for (int i = 0; i < archerLocation.size(); i++) {
				// 각 궁수 위치에서 BFS로 적을 찾는다. 찾은 적은 Set에 저장한다
				Node node = bfs(archerLocation.get(i), map);
				if (node != null) {
					set.add(node);
				}
			}
			result += set.size();
			// 적 처치
			for (Node x : set) {
				map[x.i][x.j] = 0;
			}

			// 적 전진
			for (int i = n - 1; i > 0; i--) {
				for (int j = 0; j < m; j++) {
					map[i][j] = map[i - 1][j];
				}
			}
			Arrays.fill(map[0], 0);

		}

		return result;
	}

	private static Node bfs(int start, int[][] map) {
		Node node = new Node(n - 1, start);
		boolean visited[][] = new boolean[n + 1][m];
		Queue<Node> q = new ArrayDeque<>();
		Queue<Integer> qcnt = new ArrayDeque<>();
		q.add(node);
		qcnt.add(1);
		visited[n - 1][start] = true;
		int deli[] = { 0, -1, 0 };// 좌 상우
		int delj[] = { -1, 0, 1 };
		while (!q.isEmpty()) {
			Node now = q.poll();
			int cnt = qcnt.poll();
			if (cnt > d)
				return null;// 사거리 내에 적을 발견하지 못했음
			if (map[now.i][now.j] == 1) {// 적을 발견
				return new Node(now.i, now.j);
			}
			// 자식을 넣는다

			for (int idx = 0; idx < 3; idx++) {
				int di = now.i + deli[idx];
				int dj = now.j + delj[idx];
				if (di >= 0 && dj >= 0 && di < n && dj < m && !visited[di][dj]) {
					q.add(new Node(di, dj));
					qcnt.add(cnt + 1);
				}
			}
		}
		return null;
	}

}