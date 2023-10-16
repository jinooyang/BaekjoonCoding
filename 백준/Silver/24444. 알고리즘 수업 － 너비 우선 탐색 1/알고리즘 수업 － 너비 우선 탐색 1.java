import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			list.get(u).add(v);
			list.get(v).add(u);
		}
		for (int i = 0; i < n + 1; i++) {
			Collections.sort(list.get(i));
		}
		int visited[] = new int[n + 1];
		Arrays.fill(visited, 0);
		Queue<Integer> q = new ArrayDeque<>();
		int cnt = 1;
		q.add(start);
		visited[start] = cnt++;

		while (!q.isEmpty()) {
			int now = q.poll();
			for (int x : list.get(now)) {
				if (visited[x] == 0) {
					visited[x] = cnt++;
					q.add(x);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			sb.append(visited[i]).append("\n");
		}
		System.out.println(sb);
	}
}