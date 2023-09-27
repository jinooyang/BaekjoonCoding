import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int i;
		int j;
		boolean isWater;
		int cnt;

		public Node(int i, int j, boolean isWater, int cnt) {
			super();
			this.i = i;
			this.j = j;
			this.isWater = isWater;
			this.cnt = cnt;
		}

	}

	static int deli[] = { 0, 0, 1, -1 };
	static int delj[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		char ary[][] = new char[n][m];
		List<Node> list = new ArrayList<>();
		Node start = null;
		Node dest = null;
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				ary[i][j] = s.charAt(j);
				if (ary[i][j] == '*')
					list.add(new Node(i, j, true, 0));
				if (ary[i][j] == 'D')
					dest = new Node(i, j, false, 0);
				if (ary[i][j] == 'S') {
					start = new Node(i, j, false, 0);
					ary[i][j] = '.';
				}
			}
		}

		Queue<Node> q = new ArrayDeque<>();
		boolean visited[][] = new boolean[n][m];
		visited[start.i][start.j] = true;
		for (int i = 0; i < list.size(); i++) {
			q.add(list.get(i));
		}
		q.add(start);
		int answer = -1;
		while (!q.isEmpty()) {
			Node now = q.poll();
			if (ary[now.i][now.j] == 'D') {
				answer = now.cnt;
			}
			for (int idx = 0; idx < 4; idx++) {
				int di = now.i + deli[idx];
				int dj = now.j + delj[idx];
				if (di >= 0 && dj >= 0 && di < n && dj < m) {
					if (now.isWater) {
						if (ary[di][dj] == '.') {
							ary[di][dj] = '*';
							q.add(new Node(di, dj, now.isWater, now.cnt + 1));
						}
					}
					if (!now.isWater) {
						if (!visited[di][dj] && (ary[di][dj] == '.' || ary[di][dj] == 'D')) {
							visited[di][dj] = true;
							q.add(new Node(di, dj, now.isWater, now.cnt + 1));
						}
					}
				}
			}

		}
		if (answer == -1)
			System.out.println("KAKTUS");
		else
			System.out.println(answer);
	}
}