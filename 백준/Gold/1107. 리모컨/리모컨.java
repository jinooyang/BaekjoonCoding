import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());

		boolean broken[] = new boolean[10];
		if (m > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				int b = Integer.parseInt(st.nextToken());
				broken[b] = true;
			}
		}

		int minAnswer = Math.abs(n - 100);

		Queue<Integer> q = new ArrayDeque<>();
		Queue<Integer> qcnt = new ArrayDeque<>();
		Set<Integer> visited = new HashSet<>();
		q.add(-1);
		qcnt.add(0);
		visited.add(-1);
		while (!q.isEmpty()) {
			int now = q.poll();
			int cnt = qcnt.poll();

			if (now != -1) {// 초기엔 확인 작업 안한다
				if (now == n) {// 정답이랑 같은 경우
					if (minAnswer > cnt)
						minAnswer = cnt;
				} else {
					// 현재 위치에서 + 혹은 - 로 정답까지 가는 경로는?
					int dist = Math.abs(now - n);
					dist = dist + cnt;
					if (now == 0 && cnt == 0)
						dist += 1;
					if (minAnswer > dist) {
						minAnswer = dist;
					}
				}
			}

			if (now == -1)
				now = 0;
			for (int i = 0; i < 10; i++) {
				if (!broken[i]) {
					int nextChannel = now * 10 + i;
					if (nextChannel > n + minAnswer)
						continue;
					if (!visited.contains(nextChannel)) {
						q.add(nextChannel);
						qcnt.add(cnt + 1);
						visited.add(nextChannel);
					}
				}
			}

		}
		System.out.println(minAnswer);
	}
}