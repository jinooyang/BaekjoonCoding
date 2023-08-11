import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int ary[] = new int[5001];
		// bfs
		Queue<Integer> q = new ArrayDeque<>();
		q.add(3);
		q.add(5);
		ary[3] = 1;
		ary[5] = 1;
		while (!q.isEmpty()) {
			int x = q.poll();
			if (x == n)
				break;
			if (x + 3 <= 5000 && ary[x + 3] == 0) {
				q.add(x + 3);
				ary[x + 3] = ary[x] + 1;
			}
			if (x + 5 <= 5000 && ary[x + 5] == 0) {
				q.add(x + 5);
				ary[x + 5] = ary[x] + 1;
			}
		}
		int answer = -1;
		if (ary[n] != 0)
			answer = ary[n];
		System.out.println(answer);
	}
}