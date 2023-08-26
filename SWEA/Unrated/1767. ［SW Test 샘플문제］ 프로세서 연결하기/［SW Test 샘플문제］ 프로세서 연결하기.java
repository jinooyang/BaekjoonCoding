import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	static class Node {
		int i;
		int j;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

	}

	static List<Node> list;
	static int N;
	static int deli[] = { 0, 0, 0, 1, -1 };
	static int delj[] = { 0, 1, -1, 0, 0 };
	static int ary[][];
	static int answerCoreCnt;
	static int answerMinLength;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			answerCoreCnt = 0;
			answerMinLength = Integer.MAX_VALUE;
			list = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			ary = new int[N][N];
			int coreCnt = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					ary[i][j] = Integer.parseInt(st.nextToken());
					if (ary[i][j] == 1) {
						if (i == 0 || j == 0 || i == N - 1 || j == N - 1) {
							continue;
						} else {
							coreCnt++;
							list.add(new Node(i, j));
						}
					}
				}
			}

			// 재귀를 활용해서 각 코어가 어느 방향으로 뻗을지 정한다
			// 4^12
			int coreDirectionAry[] = new int[coreCnt];
			recursion(coreCnt, 0, coreDirectionAry, 0);
			sb.append("#").append(tc).append(" ").append(answerMinLength).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int recursion(int coreCnt, int cnt, int[] coreDirectionAry, int disconnected) {

		if (answerCoreCnt > coreCnt - disconnected)
			return cnt - 1;
		if (cnt == coreCnt) {

			return check(coreDirectionAry);
		}
		for (int idx = 0; idx < 5; idx++) {
			coreDirectionAry[cnt] = idx;
			int x = recursion(coreCnt, cnt + 1, coreDirectionAry, idx == 0 ? (disconnected + 1) : disconnected);
			if (x == -1)
				continue;
			if (cnt > x)
				return x;
		}
		return -1;

	}

	private static int check(int[] coreDirectionAry) {
		Stack<Node> stack = new Stack<>();
		boolean possible = true;
		int length = 0;
		int cores = 0;
		int result = -1;
		// 전선 설치
		for (int i = 0; i < list.size(); i++) {

			Node node = list.get(i);
			int direction = coreDirectionAry[i];
			if (direction == 0) {
//				if (node.i == 0 || node.i == N - 1 || node.j == 0 || node.j == N - 1) {// 벽에 있는 코어니까 더한다
//					cores++;
//				}
				continue;
			}
			int dist = 1;

			while (true) {// 전선 연결
				int di = node.i + deli[direction] * dist;
				int dj = node.j + delj[direction] * dist;
				if (di >= 0 && dj >= 0 && di < N && dj < N) {// 범위 내에 있다면
					if (ary[di][dj] == 1) {// 전선 혹은 코어를 만났으면 현재 방식은 안된다는 뜻이다.
						possible = false;
						result = i;
						break;
					} else {
						ary[di][dj] = 1;
						length++;
						stack.add(new Node(di, dj));

					}
				} else {// 자연스럽게 벽을 만남
					cores++;
					break;
				}
				dist++;
			}

			if (!possible) {
				break;
			}
		}

		if (possible) {
			if (cores > answerCoreCnt) {
				answerCoreCnt = cores;
				answerMinLength = length;
			}
			if (cores == answerCoreCnt) {
				if (length < answerMinLength)
					answerMinLength = length;
			}
		}

		while (!stack.isEmpty()) {
			Node delNode = stack.pop();
			ary[delNode.i][delNode.j] = 0;
		}
		//System.out.println(result);
		return result;
	}
}