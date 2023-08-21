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
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());// 건물의 개수 N
			int k = Integer.parseInt(st.nextToken());// 건물간의 건설순서 규칙의 총 개수 K
			st = new StringTokenizer(br.readLine());
			int time[] = new int[n + 1];// 건물 짓는 시간
			int answer[] = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				time[i] = Integer.parseInt(st.nextToken());
				answer[i] = time[i];
			}
			int building[] = new int[n + 1];// depth

			// Arrays.fill(answer, 0);
			List<List<Integer>> list = new ArrayList<>();

			for (int i = 0; i < n + 1; i++) {
				list.add(new ArrayList<>());
			}
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				building[b]++;
				list.get(a).add(b);
			}
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			Queue<Integer> q = new ArrayDeque<>();
			for (int i = 1; i <= n; i++) {
				if (building[i] == 0) {
					q.add(i);
				}
			}
			while (!q.isEmpty()) {
				int now = q.poll();
				for (int i = 0; i < list.get(now).size(); i++) {
					int next = list.get(now).get(i);
					int newTime = answer[now] + time[next];
					building[next]--;
					if (answer[next] < newTime)
						answer[next] = newTime;
					if (building[next] == 0) {
						q.add(next);
					}
				}
			}
			System.out.println(answer[w]);
		}
	}
}