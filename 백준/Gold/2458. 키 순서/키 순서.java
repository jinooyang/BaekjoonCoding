import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		List<List<Integer>> list = new ArrayList<>();
		List<List<Integer>> list2 = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			list.add(new ArrayList<>());
			list2.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list2.get(b).add(a);
		}
		int answer = 0;
		for (int i = 1; i <= n; i++) {
			boolean visited[][] = new boolean[n + 1][2];
			Queue<Integer> q = new ArrayDeque<>();
			visited[i][0] = true;
			q.add(i);
			while (!q.isEmpty()) {
				int now = q.poll();
				for (Integer next : list.get(now)) {
					if (!visited[next][0]) {
						visited[next][0] = true;
						q.add(next);
					}
				}
			}

			visited[i][1] = true;
			q.add(i);
			while (!q.isEmpty()) {
				int now = q.poll();
				for (Integer next : list2.get(now)) {
					if (!visited[next][1]) {
						visited[next][1] = true;
						q.add(next);
					}
				}
			}
			boolean isAnswer = true;
			for (int k = 1; k < n + 1; k++) {
				if (!visited[k][0] && !visited[k][1]) {
					isAnswer = false;
					break;
				}
			}
			if (isAnswer) {
				answer++;
			}
		}
		System.out.println(answer);
	}
}