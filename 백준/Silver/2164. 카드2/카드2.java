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
		Queue<Integer> dq = new ArrayDeque<>();
		for (int i = 1; i <= n; i++) {
			dq.add(i);
		}
		while (dq.size() > 1) {
			dq.poll();
			if (dq.size() == 1)
				break;
			int temp = dq.poll();
			dq.add(temp);
		}
		System.out.println(dq.peek());
	}
}