import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int height;
		int index;

		public Node(int height, int index) {
			super();
			this.height = height;
			this.index = index;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		List<Node> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			list.add(new Node(Integer.parseInt(st.nextToken()), i + 1));
		}
		int[] answer = new int[n];

		Stack<Node> stack = new Stack<>();
		int maxHeight = 0;
		for (int i = 0; i < n; i++) {
			if (list.get(i).height >= maxHeight) {
				stack.clear();
				stack.add(list.get(i));
				answer[i] = 0;
				maxHeight = list.get(i).height;
				continue;
			}

			if (list.get(i).height < stack.peek().height) {
				Node node = stack.peek();
				stack.add(list.get(i));
				answer[i] = node.index;
			} else {

				while (!stack.isEmpty() && stack.peek().height < list.get(i).height) {
					stack.pop();
				}
				if (stack.isEmpty()) {
					maxHeight = list.get(i).height;
					stack.add(list.get(i));
					answer[i] = 0;
					continue;
				}
				if (stack.peek().height == list.get(i).height) {
					answer[i] = stack.peek().index;
					stack.pop();
					stack.add(list.get(i));

				} else {
					
					answer[i] = stack.peek().index;
					stack.add(list.get(i));
				}

			}

		}

		for (int i = 0; i < n; i++) {
			System.out.print(answer[i] + " ");
		}
	}
}