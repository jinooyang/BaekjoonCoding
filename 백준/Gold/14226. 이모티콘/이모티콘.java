import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int cnt;
		int time;
		int clip;

		public Node(int cnt, int time, int clip) {
			super();
			this.cnt = cnt;
			this.time = time;
			this.clip = clip;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());

		// 복사 1초

		// 붙여넣기 1초

		// 1삭제 1초
		int answer = Integer.MAX_VALUE;
		Queue<Node> q = new ArrayDeque<>();
		int visited[][] = new int[1001][1001];
		for (int i = 0; i < 1001; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		q.add(new Node(1, 0, 0));
		while (!q.isEmpty()) {
			Node now = q.poll();
			int cnt = now.cnt;
			int time = now.time;
			int clip = now.clip;

			if (cnt == S) {
				answer = Math.min(answer, time);

			}

			// 1. 복사하기
			if (cnt != clip) {
				q.add(new Node(cnt, time + 1, cnt));
			}
			// 2. 붙여넣기
			if (cnt + clip < 1001 && visited[cnt + clip][clip] > time + 1) {
				q.add(new Node(cnt + clip, time + 1, clip));
				visited[cnt + clip][clip] = time + 1;
			}
			// 3. 1개 삭제
			if (cnt - 1 > 0 && visited[cnt - 1][clip] > time + 1) {
				q.add(new Node(cnt - 1, time + 1, clip));
				visited[cnt - 1][clip] = time + 1;
			}
		}
		System.out.println(answer);
	}
}