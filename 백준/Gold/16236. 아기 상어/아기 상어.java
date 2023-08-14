import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static class Node {
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
		public String toString() {
			return "Node [i=" + i + ", j=" + j + ", dist=" + dist + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int ary[][] = new int[n][n];
		Node babyShark = null;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
				if (ary[i][j] == 9) {
					babyShark = new Node(i, j, 0);
					ary[i][j] = 0;
				}
			}
		}
		// 입력 완료
		int answer = 0;
		int babySharkSize = 2;
		int foodcnt = 0;
		int deli[] = { 0, 0, 1, -1 };
		int delj[] = { 1, -1, 0, 0 };
		while (true) {
			Node nextNode = new Node(0, 0, Integer.MAX_VALUE);
			// bfs로 다음 이동할 좌표를 구한다
			boolean check[][] = new boolean[n][n];
			check[babyShark.i][babyShark.j] = true;
			Queue<Node> q = new ArrayDeque<>();
			q.add(babyShark);
			boolean foundfish = false;
			while (!q.isEmpty()) {
				Node now = q.poll();
				//System.out.println(now);
				int i = now.i;
				int j = now.j;
				int dist = now.dist;
				if (ary[i][j] < babySharkSize && ary[i][j] != 0 && dist <= nextNode.dist) {// 먹을수 있는물고기가 있는 노드일경우

					foundfish = true;
					if (dist == nextNode.dist) {// 거리가 같은경우
						if (i < nextNode.i) {// 거리가 같은 경우 i가 작은거
							nextNode.i = i;
							nextNode.j = j;
							nextNode.dist = dist;
							continue;
						}
						if (i == nextNode.i) {// i가 같은 경우 j가 작은거
							if (j < nextNode.j) {
								nextNode.i = i;
								nextNode.j = j;
								nextNode.dist = dist;
								continue;
							}
						}

					}
					if (dist < nextNode.dist) {// 거리가 작은경우 더이상 자식을 볼 필요없음
						nextNode.i = i;
						nextNode.j = j;
						nextNode.dist = dist;
						continue;
					}

				}
				if (dist > nextNode.dist) {// 거리가 더 먼 경우
					break;
				}

				// 이제 자식을 넣자
				for (int idx = 0; idx < 4; idx++) {
					int di = i + deli[idx];
					int dj = j + delj[idx];
					if (di >= 0 && di < n && dj >= 0 && dj < n && !check[di][dj]) {// 범위 안에 있고 방문하지 않은 노드의 경우
						if (ary[di][dj] <= babySharkSize) {// 갈 수 있는 위치인 경우
							q.add(new Node(di, dj, dist + 1));
							check[di][dj] = true;
						}
					}
				}
			}

			// 다음 노드로 이동한다
			if (!foundfish)
				break;
			babyShark.i = nextNode.i;
			babyShark.j = nextNode.j;
//			System.out.println(
//					babyShark.i + ", " + babyShark.j + "에 있는" + ary[babyShark.i][babyShark.j] + " 크기 물고기 섭취완료");
			ary[babyShark.i][babyShark.j] = 0;// 물고기 섭취 완료
			foodcnt++;
			if (babySharkSize == foodcnt) {
				foodcnt = 0;
				babySharkSize++;
			}
	//		System.out.println("아기 상어 크기 is now : " + babySharkSize);
			answer += nextNode.dist;

		}
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print(ary[i][j] + " ");
//			}
//			System.out.println();
//		}
		System.out.println(answer);

	}
}