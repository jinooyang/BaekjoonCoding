import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Solution {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =null;
		int T = 10;
		for (int tc = 1; tc <= T; tc++) {
			Stack<Character> stack = new Stack<>();
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int correctStack = 1;
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int i = 0; i < num; i++) {
				char temp = s.charAt(i);
				if (stack.isEmpty()) {
					if (temp == '}' || temp == '>' || temp == ')' || temp == ']') {// 닫는 괄호 일 경우
						correctStack = 0;
						break;
					} else {
						stack.add(temp);
						continue;
					}
				}
				char top = stack.peek();
				if (temp == '}' || temp == '>' || temp == ')' || temp == ']') {// 닫는 괄호 일 경우
					if ((top == '{' && temp == '}') || (top == '(' && temp == ')') || (top == '[' && temp == ']')
							|| (top == '<' && temp == '>')) {// 짝이 맞는 경우
						stack.pop();
					} else {// 짝이 맞지 않는 경우
						correctStack = 0;
						break;
					}
				} else {// 여는 괄호 경우...스택에 추가한다
					stack.add(temp);
				}
			}
			System.out.println("#" + tc + " " + correctStack);
		}
	}
}