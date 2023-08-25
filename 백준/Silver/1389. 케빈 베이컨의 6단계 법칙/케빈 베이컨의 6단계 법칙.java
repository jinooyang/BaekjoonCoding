import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static List<List<Integer>> list;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list.get(b).add(a);
		}
		int minKB = Integer.MAX_VALUE;
		int answer = 0;
		for (int i = 1; i <= n; i++) {
			int kevinBacon = bfs(i);
			if (kevinBacon < minKB) {
				minKB = kevinBacon;
				answer = i;
			}
		}
		System.out.println(answer);
	}

	private static int bfs(int i) {
		int visit[] = new int[n + 1];
		for (int x = 1; x <= n; x++) {
			visit[x] = -1;
		}
		Queue<Integer> q = new ArrayDeque<>();
		Queue<Integer> qcnt = new ArrayDeque<>();

		q.add(i);
		qcnt.add(0);
		visit[i] = 0;
		while (!q.isEmpty()) {
			int now = q.poll();
			int cnt = qcnt.poll();
			for (int x = 0; x < list.get(now).size(); x++) {
				if (visit[list.get(now).get(x)] == -1) {
					q.add(list.get(now).get(x));
					qcnt.add(cnt + 1);
					visit[list.get(now).get(x)] = cnt + 1;
				}
			}
		}

		int result = 0;
		for (int x = 1; x <= n; x++) {
			result += visit[x];
		}
		return result;
	}
}