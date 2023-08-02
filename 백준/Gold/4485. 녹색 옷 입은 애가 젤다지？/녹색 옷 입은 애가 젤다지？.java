import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int i;
		int j;
		int dist;

		public Node(int i, int j, int dist) {
			super();
			this.i = i;
			this.j = j;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = null;
		int problem = 1;
		while (true) {

			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			if (n == 0)
				break;
			int ary[][] = new int[n][n];
			for (int y = 0; y < n; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < n; x++) {
					ary[y][x] = Integer.parseInt(st.nextToken());
				}
			}

			// 입력 완료

			int distance[][] = new int[n][n];
			for (int i = 0; i < n; i++) {
				Arrays.fill(distance[i], Integer.MAX_VALUE);
			}

			int deli[] = { -1, 1, 0, 0 };
			int delj[] = { 0, 0, -1, 1 };

			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(0, 0, ary[0][0]));
			distance[0][0] = ary[0][0];
			while (!pq.isEmpty()) {

				Node node = pq.poll();
				int nowi = node.i;
				int nowj = node.j;
				int dist = node.dist;
				if (dist > distance[nowi][nowj]) {
					continue;
				}
				// nextNode확인하기
				for (int idx = 0; idx < 4; idx++) {
					int nexti = nowi + deli[idx];
					int nextj = nowj + delj[idx];
					if (nexti >= 0 && nextj >= 0 && nexti < n && nextj < n) {
						int nextNodeDist = ary[nexti][nextj];

						int newDist = distance[nowi][nowj] + nextNodeDist;
						if (newDist < distance[nexti][nextj]) {
							distance[nexti][nextj] = newDist;
							pq.add(new Node(nexti, nextj, newDist));
						}
					}

				}
			}
			System.out.println("Problem " + problem + ": " + distance[n - 1][n - 1]);
			problem++;
		}
	}
}