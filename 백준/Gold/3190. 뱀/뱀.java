import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
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

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int ary[][] = new int[n][n];
		boolean check[][] = new boolean[n][n];
		check[0][0] = true;
		Deque<Node> q = new ArrayDeque<>();
		q.add(new Node(0, 0));
		st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int applei = Integer.parseInt(st.nextToken()) - 1;
			int applej = Integer.parseInt(st.nextToken()) - 1;
			ary[applei][applej] = 1;
		}
//		System.out.println("------------------------");
//		for (int qi = 0; qi < n; qi++) {
//			for (int qj = 0; qj < n; qj++) {
//				System.out.print(ary[qi][qj]);
//				System.out.print(" ");
//			}
//			System.out.println();
//		}
//		System.out.println("------------------------");

		int deli[] = { 0, 1, 0, -1 };// 우 하 좌 상
		int delj[] = { 1, 0, -1, 0 };
		int dir = 200;
		st = new StringTokenizer(br.readLine());
		int l = Integer.parseInt(st.nextToken());
		boolean isGameOver = false;
		int T = 0;
		int beforeNum = 0;
		for (int i = 0; i < l; i++) {
			// 명령을 받는다

			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) - beforeNum;
			beforeNum += num;
			char direction = st.nextToken().charAt(0);
			if (!isGameOver) {

				for (int d = 0; d < num; d++) {
				//	System.out.println(T);
					T++;
					// 다음 노드 위치
					Node node = q.peekLast();
					int nowi = node.i;
					int nowj = node.j;
				//	System.out.println("head : " + nowi + " " + nowj);
					int di = nowi + deli[dir % 4];
					int dj = nowj + delj[dir % 4];
				//	System.out.println("next : " + di + " " + dj);
					// 꼬리를 제거하고 생각해야함
//					for (int ii = 0; ii < n; ii++) {
//						for (int jj = 0; jj < n; jj++) {
//							System.out.print(check[ii][jj] ? 1 : 0);
//							System.out.print(" ");
//						}
//						System.out.println();
//					}
//					Node tail = q.peek();
//					check[tail.i][tail.j] = false;
					if (di >= 0 && di < n && dj >= 0 && dj < n && !check[di][dj]) {
						if (ary[di][dj] == 1) {// 사과일 경우
							q.add(new Node(di, dj));
							check[di][dj] = true;
							ary[di][dj] = 0;
						} else {
							Node tail = q.poll();
							check[tail.i][tail.j] = false;
							q.add(new Node(di, dj));
							check[di][dj] = true;
						}
					} else {
						isGameOver = true;
						break;
					}
				}
				if (direction == 'D') {
					dir++;
				} else if (direction == 'L') {
					dir--;
				}
			}
			if (i == l - 1) {
			}
		}

		while (!isGameOver) {

			//System.out.println(T);
			T++;
			Node node = q.peekLast();
			int nowi = node.i;
			int nowj = node.j;
		//	System.out.println("head : " + nowi + " " + nowj);
			int di = nowi + deli[dir % 4];
			int dj = nowj + delj[dir % 4];
//			System.out.println("next : " + di + " " + dj);
//			for (int ii = 0; ii < n; ii++) {
//				for (int jj = 0; jj < n; jj++) {
//					System.out.print(check[ii][jj] ? 1 : 0);
//					System.out.print(" ");
//				}
//				System.out.println();
//			}

			if (di >= 0 && di < n && dj >= 0 && dj < n && !check[di][dj]) {
				if (ary[di][dj] == 1) {// 사과일 경우
					q.add(new Node(di, dj));
					check[di][dj] = true;
					ary[di][dj] = 0;
				}else {
					Node tail = q.poll();
					check[tail.i][tail.j] = false;
					q.add(new Node(di, dj));
					check[di][dj] = true;
				}

			} else {
				isGameOver = true;

			}
		}
		System.out.println(T);

	}
}