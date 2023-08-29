import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int x;
		int cnt;

		public Node(int x, int cnt) {
			super();
			this.x = x;
			this.cnt = cnt;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		Queue<Node> q = new ArrayDeque<>();
		boolean visited[] = new boolean[1000001];
		q.add(new Node(n, 0));
		while (!q.isEmpty()) {
			Node k = q.poll();
			if (k.x == 1) {
				System.out.println(k.cnt);
				break;
			}
			if (k.x % 3 == 0 && !visited[k.x / 3]) {
				visited[k.x / 3] = true;
				q.add(new Node(k.x / 3, k.cnt + 1));
			}
			if (k.x % 2 == 0 && !visited[k.x / 2]) {
				visited[k.x / 2] = true;
				q.add(new Node(k.x / 2, k.cnt + 1));
			}
			if (!visited[k.x - 1]) {
				visited[k.x - 1] = true;
				q.add(new Node(k.x - 1, k.cnt + 1));
			}

		}

	}
}