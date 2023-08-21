import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = null;
		while (true) {
			//st = new StringTokenizer(br.readLine());
			String s = br.readLine();
			if (s.equals("."))
				break;
			Stack<Character> stack = new Stack<>();
			boolean ans = true;
			for (int i = 0; i < s.length(); i++) {
				//System.out.print(s.charAt(i));
				if (s.charAt(i) == '(' || s.charAt(i) == '[') {
					stack.add(s.charAt(i));
				}
				if (s.charAt(i) == ')') {
					if (stack.isEmpty() || stack.peek() != '(') {
						// System.out.println("small no pair");
						ans = false;
					} else
						stack.pop();
				}
				if (s.charAt(i) == ']') {
					if (stack.isEmpty() || stack.peek() != '[') {
						// System.out.println("big no pair");
						ans = false;
					} else
						stack.pop();
				}
			}
			if (!stack.isEmpty()) {
				//System.out.println("stack is not empty");

				ans = false;
				stack.clear();
			}
			if (ans)
				System.out.println("yes");
			else
				System.out.println("no");
		}
	}
}