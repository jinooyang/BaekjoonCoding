import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = 10;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());// 입력 받는 데이터의 길이
			int m = Integer.parseInt(st.nextToken());// 시작점

			List<List<Integer>> list = new ArrayList<>();
			for (int i = 0; i < 101; i++) {
				list.add(new ArrayList<>());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				list.get(from).add(to);
			}
			boolean visited[] = new boolean[101];
			Queue<Integer> q = new ArrayDeque<>();
			Queue<Integer> qcnt = new ArrayDeque<>();

			q.add(m);
			qcnt.add(0);
			visited[m] = true;
			int maxCnt = 0;
			int maxPerson = 0;
			while (!q.isEmpty()) {
				int now = q.poll();
				int cnt = qcnt.poll();
				if (cnt > maxCnt) {
					maxCnt = cnt;
					maxPerson = now;
				}
				if (cnt == maxCnt) {
					if (now > maxPerson) {
						maxPerson = now;
					}
				}
				for (Integer x : list.get(now)) {
					if (!visited[x]) {
						visited[x] = true;
						q.add(x);
						qcnt.add(cnt + 1);
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(maxPerson).append("\n");
		}
		System.out.println(sb.toString());
	}

}