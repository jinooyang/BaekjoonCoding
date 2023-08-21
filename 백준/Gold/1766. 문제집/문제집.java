import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			list.add(new ArrayList<>());
		}
		int problem[] = new int[n + 1];
		boolean printed[] = new boolean[n + 1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			problem[b]++;
			list.get(a).add(b);
		}
		PriorityQueue<Integer> q = new PriorityQueue<>();
		for (int i = 1; i < n + 1; i++) {

			if (problem[i] == 0)
				q.add(i);

			while (!q.isEmpty()) {
				int x = q.poll();
				sb.append(x).append(" ");
				problem[x] = -1;
				for (int j = 0; j < list.get(x).size(); j++) {
					problem[list.get(x).get(j)]--;
					if (problem[list.get(x).get(j)] == 0)
						q.add(list.get(x).get(j));
				}

			}

		}
		System.out.println(sb.toString());
	}
}