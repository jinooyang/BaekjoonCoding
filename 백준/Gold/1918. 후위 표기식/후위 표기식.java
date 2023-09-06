import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		char op;
		int num;
		int idx;

		public Node(char op, int num, int idx) {
			super();
			this.op = op;
			this.num = num;
			this.idx = idx;
		}

		@Override
		public int compareTo(Node o) {
			if (this.num != o.num)
				return Integer.compare(this.num, o.num);// 더 큰게 우선순위다
			else {
				return Integer.compare(this.idx, o.idx) * -1;// 더 작은게 우선순위다
			}
		}

	}

	static StringBuilder sb;
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String s = st.nextToken();
		sb = new StringBuilder();
		int bracket = 0;
		pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < s.length(); i++) {

			char now = s.charAt(i);
			if (now == '(') {
				bracket++;
			}
			if (now == ')') {
				bracket--;
			}
			if (now >= 'A' && now <= 'Z') {
				sb.append(now);
			}
			// 연산자가 들어오면 PQ에서 나보다 같거나 높은 연산자를 모두 출력한다.
			int number = 3 * bracket + 1;
			if (now == '*') {

				printOperator(number + 1);
				pq.add(new Node(now, number + 1, i));

			}
			if (now == '/') {
				printOperator(number + 1);
				pq.add(new Node(now, number + 1, i));
			}
			if (now == '+') {
				printOperator(number);
				pq.add(new Node(now, number, i));
			}
			if (now == '-') {
				printOperator(number);
				pq.add(new Node(now, number, i));
			}

		}
		printOperator(-1);
		System.out.println(sb);
	}

	private static void printOperator(int i) {
		while (!pq.isEmpty()) {
			if (pq.peek().num >= i) {
				sb.append(pq.poll().op);
			} else
				break;
		}
	}

}