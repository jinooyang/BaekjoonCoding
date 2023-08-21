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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());// 입력
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int student[] = new int[n + 1];
		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());// 입력
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			student[b]++;
			list.get(a).add(b);
		}

		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i < n + 1; i++) {
			if (student[i] == 0)
				q.add(i);
		}

		while (!q.isEmpty()) {
			int now = q.poll();
			sb.append(now).append(" ");
			for (int i = 0; i < list.get(now).size(); i++) {
				student[list.get(now).get(i)]--;
				if (student[list.get(now).get(i)] == 0)
					q.add(list.get(now).get(i));
			}
		}
		System.out.println(sb.toString());
	}
}