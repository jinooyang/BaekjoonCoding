import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
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
		int m = Integer.parseInt(st.nextToken());
		char ary[][] = new char[n][m];
		Queue<Node> q = new ArrayDeque<>();
		boolean visited[][] = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < m; j++) {
				ary[i][j] = s.charAt(j);
				if (ary[i][j] == 'I') {
					q.add(new Node(i, j));
					visited[i][j] = true;
				}
			}
		}
		int answer = 0;
		int deli[] = { 0, 0, 1, -1 };
		int delj[] = { 1, -1, 0, 0 };
		while (!q.isEmpty()) {
			Node now = q.poll();
			int i = now.i;
			int j = now.j;
			if (ary[i][j] == 'P')
				answer++;
			for (int idx = 0; idx < 4; idx++) {
				int di = deli[idx] + i;
				int dj = delj[idx] + j;
				if (di >= 0 && dj >= 0 && di < n && dj < m && !visited[di][dj] && ary[di][dj] != 'X') {
					visited[di][dj] = true;
					q.add(new Node(di, dj));
				}
			}
		}
		if (answer == 0)
			System.out.println("TT");
		else
			System.out.println(answer);
	}
}