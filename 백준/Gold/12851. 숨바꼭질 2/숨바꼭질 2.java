import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int vis[] = new int[200000];
		Arrays.fill(vis, Integer.MAX_VALUE);
		Queue<Integer> q = new ArrayDeque<>();
		Queue<Integer> qcnt = new ArrayDeque<>();

		q.add(N);
		qcnt.add(0);
		vis[N] = 0;
		int answer = Integer.MAX_VALUE;
		int answerCnt = 0;
		while (!q.isEmpty()) {
			int now = q.poll();
			int cnt = qcnt.poll();
			if (now == K) {
				if (cnt == answer) {
					answer = cnt;
					answerCnt++;
				}
				if (cnt < answer) {
					answer = cnt;
					answerCnt = 1;
				}

			}
			if (cnt >= answer)
				continue;
			if (now + 1 < 200000 && vis[now + 1] >= cnt + 1) {
				q.add(now + 1);
				qcnt.add(cnt + 1);
				vis[now + 1] = cnt + 1;
			}
			if (now - 1 >= 0 && vis[now - 1] >= cnt + 1) {
				q.add(now - 1);
				qcnt.add(cnt + 1);
				vis[now - 1] = cnt + 1;
			}
			if (now * 2 < 200000 && vis[now * 2] >= cnt + 1) {
				q.add(now * 2);
				qcnt.add(cnt + 1);
				vis[now * 2] = cnt + 1;
			}
		}

		System.out.println(answer);
		System.out.println(answerCnt);
	}
}