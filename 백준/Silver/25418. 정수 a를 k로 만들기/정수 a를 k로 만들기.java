import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		boolean vis[] = new boolean[m + 1];
		Queue<Integer> q = new ArrayDeque<>();
		Queue<Integer> qcnt = new ArrayDeque<>();
		q.add(n);
		qcnt.add(0);
		vis[n] = true;
		int answer = 0;
		while (!q.isEmpty()) {
			int now = q.poll();
			int cnt = qcnt.poll();
			if (now == m) {
				answer = cnt;
				break;
			}
			if (now + 1 <= m && vis[now + 1] == false) {
				q.add(now + 1);
				qcnt.add(cnt + 1);
				vis[now+1]=true;
			}
			if (now * 2 <= m && vis[now*2]==false) {
				q.add(now * 2);
				qcnt.add(cnt + 1);
				vis[now*2]=true;
			}
		}
		System.out.println(answer);
	}
}