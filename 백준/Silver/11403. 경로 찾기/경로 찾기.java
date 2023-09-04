import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int x = Integer.parseInt(st.nextToken());
				if (x == 1) {
					list.get(i).add(j);
				}
			}
		}
		int answer[][] = new int[n][n];

		for (int i = 0; i < n; i++) {
			Queue<Integer> q = new ArrayDeque<>();
			q.add(i);
			while (!q.isEmpty()) {
				int now = q.poll();
				for (int node = 0; node < list.get(now).size(); node++) {
					int nextNode = list.get(now).get(node);
					if (answer[i][nextNode] == 0) {
						answer[i][nextNode] = 1;
						q.add(nextNode);
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(answer[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}
}