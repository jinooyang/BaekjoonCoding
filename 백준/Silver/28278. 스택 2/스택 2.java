import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int flag = Integer.parseInt(st.nextToken());
			switch (flag) {
			case 1:
				int x = Integer.parseInt(st.nextToken());
				stack.add(x);
				break;
			case 2:
				if (stack.isEmpty()) {
					sb.append(-1).append("\n");
				} else
					sb.append(stack.pop()).append("\n");
				break;
			case 3:
				sb.append(stack.size()).append("\n");
				break;
			case 4:
				if (stack.isEmpty())
					sb.append(1).append("\n");
				else
					sb.append(0).append("\n");
				break;
			case 5:
				if (stack.isEmpty()) {
					sb.append(-1).append("\n");
				} else
					sb.append(stack.peek()).append("\n");
				break;

			default:
				break;
			}
		}
		System.out.println(sb.toString());
	}
}