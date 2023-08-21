import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());// 입력
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Queue<Integer> q = new ArrayDeque<>();
		Queue<Integer> qcnt = new ArrayDeque<>();
		int visited[] = new int[100001];
		q.add(N);
		qcnt.add(0);
		while (!q.isEmpty()) {
			int x = q.poll();
			int cnt = qcnt.poll();
			if (x == K)
				break;
			if (x + 1 < 100001 && visited[x + 1] == 0) {
				visited[x + 1] = cnt + 1;
				q.add(x + 1);
				qcnt.add(cnt + 1);
			}
			if (x - 1 >= 0 && visited[x - 1] == 0) {
				visited[x - 1] = cnt + 1;
				q.add(x - 1);
				qcnt.add(cnt + 1);
			}
			if (x * 2 < 100001 && visited[x * 2] == 0) {
				visited[x * 2] = cnt + 1;
				q.add(x * 2);
				qcnt.add(cnt + 1);
			}
		}
		System.out.println(visited[K]);
	}
}