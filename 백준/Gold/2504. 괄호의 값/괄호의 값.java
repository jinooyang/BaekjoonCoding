import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String s = st.nextToken();
		Stack<Character> stack = new Stack<>();
		int answer = 0;
		int mul = 1;
		boolean notanswer = false;
		char before = '0';
		for (int i = 0; i < s.length(); i++) {
			char bracket = s.charAt(i);
			switch (bracket) {
			case '(':
				mul = mul * 2;
				stack.add(bracket);
				before = bracket;
				break;
			case '[':
				mul = mul * 3;
				stack.add(bracket);
				before = bracket;
				break;
			case ')':
				if (!stack.isEmpty() && stack.peek() == '(') {
					stack.pop();
					if (before != ')' && before != ']')
						answer += mul;
					mul = mul / 2;
				} else {
					notanswer = true;
				}
				before = bracket;
				break;
			case ']':

				if (!stack.isEmpty() && stack.peek() == '[') {
					stack.pop();
					if (before != ')' && before != ']')
						answer += mul;
					mul = mul / 3;
				} else {
					notanswer = true;
				}
				before = bracket;
				break;
			}

			if (notanswer) {
				answer = 0;
				break;
			}
			// System.out.println(answer);
		}
		if (!stack.isEmpty())
			answer = 0;
		System.out.println(answer);

	}
}