import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Q = Integer.parseInt(st.nextToken());
		Deque<Integer> dq = new ArrayDeque<>();
		int x = 0;
		StringBuilder sb = new StringBuilder();
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int flag = Integer.parseInt(st.nextToken());
			switch (flag) {
			case 1:
				x = Integer.parseInt(st.nextToken());
				dq.addFirst(x);
				break;
			case 2:
				x = Integer.parseInt(st.nextToken());
				dq.addLast(x);
				break;
			case 3:
				if (!dq.isEmpty()) {
					sb.append(dq.pollFirst()).append("\n");
				} else {
					sb.append("-1\n");
				}
				break;
			case 4:
				if (!dq.isEmpty()) {
					sb.append(dq.pollLast()).append("\n");
				} else {
					sb.append("-1\n");
				}
				break;
			case 5:
				sb.append(dq.size()).append("\n");
				break;
			case 6:
				if (dq.isEmpty()) {
					sb.append("1\n");
				} else {
					sb.append("0\n");
				}
				break;
			case 7:
				if (!dq.isEmpty()) {
					sb.append(dq.peekFirst()).append("\n");
				} else {
					sb.append("-1\n");
				}
				break;
			case 8:
				if (!dq.isEmpty()) {
					sb.append(dq.peekLast()).append("\n");
				} else {
					sb.append("-1\n");
				}
				break;

			default:
				break;
			}
		}
		System.out.println(sb.toString());
	}
}