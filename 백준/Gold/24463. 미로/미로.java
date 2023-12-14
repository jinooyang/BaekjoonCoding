import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
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

	static int deli[] = { 0, 0, 1, -1 };
	static int delj[] = { 1, -1, 0, 0 };
	static int N;
	static int M;
	static boolean foundAnswer = false;
	static Node exit[];

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		char ary[][] = new char[N][M];
		exit = new Node[2];
		int exitIdx = 0;

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				char temp = s.charAt(j);
				if (temp == '.')
					temp = '@';
				ary[i][j] = temp;

				if (i == 0 || j == 0 || i == N - 1 || j == M - 1) {
					if (ary[i][j] == '@') {
						exit[exitIdx] = new Node(i, j);
						exitIdx++;
					}
				}
			}
		}
//		System.out.println(exit[0].i + " " + exit[0].j);
//		System.out.println(exit[1].i + " " + exit[1].j);
		// dfs
		Stack<Node> stack = new Stack<>();
		stack.add(exit[0]);
		ary[exit[0].i][exit[0].j] = '.';
		dfs(ary, stack);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(ary[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(char[][] ary, Stack<Node> stack) {
		if (stack.isEmpty())
			return;
		if (foundAnswer)
			return;
		Node now = stack.pop();
//		System.out.println(now.i + " " + now.j);
		if (now.i == exit[1].i && now.j == exit[1].j) {
//			System.out.println("foundAnswer : " + now.i + " " + now.j);
			foundAnswer = true;
			return;
		}
		for (int idx = 0; idx < 4; idx++) {
			int di = now.i + deli[idx];
			int dj = now.j + delj[idx];

			if (di >= 0 && dj >= 0 && di < N && dj < M && ary[di][dj] == '@') {

				ary[di][dj] = '.';
				stack.add(new Node(di, dj));
				dfs(ary, stack);
				if (foundAnswer)
					break;
				ary[di][dj] = '@';
			}
		}
	}
}