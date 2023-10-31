import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		boolean isQ[] = new boolean[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int x = Integer.parseInt(st.nextToken());
			if (x == 0)
				isQ[i] = true;
		}

		Deque<Integer> dq = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int x = Integer.parseInt(st.nextToken());
			if (isQ[i]) {
				dq.addLast(x);
			}
		}
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		//System.out.println(dq.toString());
		for (int i = 0; i < m; i++) {
			
			int x = Integer.parseInt(st.nextToken());
			dq.addFirst(x);
			sb.append(dq.pollLast()).append(" ");
		
			//System.out.println(dq.toString());
		}
		System.out.println(sb);
	}
}