import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int x;
		int idx;

		public Node(int x, int idx) {
			super();
			this.x = x;
			this.idx = idx;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int answer[] = new int[n];
		Arrays.fill(answer, -1);
		Stack<Node> s = new Stack<>();
		// 스택에서 나보다 작은 것들은 모두 빼고 answer에 정답을 기록한다
		for (int i = 0; i < n; i++) {
			int temp = Integer.parseInt(st.nextToken());// 입력
			while (!s.isEmpty() && s.peek().x < temp) {
				Node node = s.pop();
				answer[node.idx] = temp;
			}
			s.add(new Node(temp, i));
		}
		// 출력
		for (int i = 0; i < n; i++) {
			sb.append(answer[i]).append(" ");
		}
		System.out.print(sb);
	}
}