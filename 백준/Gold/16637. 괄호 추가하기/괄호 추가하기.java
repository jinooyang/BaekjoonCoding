import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int maxAnswer = Integer.MIN_VALUE;

	public static int calc(List<Character> list) {
		Stack<Integer> stack = new Stack<>();
		boolean bracket = false;
		int startIndex = 0;
		int endIndex = 0;
		int mulForLater = 1;
		for (int i = list.size() - 1; i >= 0; i--) {
			char now = list.get(i);
			if (now == '(') {
				bracket = false;
				startIndex = i + 1;
				int temp = calc(list.subList(startIndex, endIndex));
				stack.add(temp * mulForLater);
				mulForLater = 1;
			}
			if (now == ')') {
				bracket = true;
				endIndex = i;
			}
			if (!bracket) {
				if (now >= '0' && now <= '9') {// 숫자 일 경우

					stack.add(now - '0');
				}
				if (now == '-') {

					int temp = stack.pop();
					temp = temp * -1;
					stack.add(temp);

				}

				if (now == '*') {

					int temp = stack.pop();
					if (list.get(i - 1) != ')') {
						int mul = temp * (list.get(--i) - '0');
						stack.add(mul);
					} else {
						mulForLater = temp;
					}
				}
			}
		}
		int answer = 0;
		while (!stack.isEmpty()) {
			int temp = stack.pop();
			answer += temp;
			// System.out.println(temp);
		}
		return answer;
	}

	public static int calc2(List<Character> list) {
		Stack<Integer> stack = new Stack<>();
		boolean bracket = false;
		int startIndex = 0;
		int endIndex = 0;
		int mulForLater = 1;
		for (int i = 0; i < list.size(); i++) {
			char now = list.get(i);
			if (now == ')') {
				bracket = false;
				endIndex = i + 1;
				int temp = calc(list.subList(startIndex, endIndex));
				stack.add(temp * mulForLater);
				mulForLater = 1;
			}
			if (now == '(') {
				bracket = true;
				startIndex = i;
			}
			if (!bracket) {
				if (now >= '0' && now <= '9') {// 숫자 일 경우

					stack.add(now - '0');
				}
				if (now == '-') {

					int temp = stack.pop();
					if (list.get(i + 1) != '(') {
						int sub = temp - (list.get(++i) - '0');
						stack.add(sub);
					} else {
						mulForLater = -1;
						stack.add(temp);
					}

				}

				if (now == '*') {

					int temp = stack.pop();
					if (list.get(i + 1) != '(') {
						int mul = temp * (list.get(++i) - '0');
						stack.add(mul);
					} else {
						mulForLater = temp;
					}
				}

				int sum = 0;
				while (!stack.isEmpty()) {
					sum += stack.pop();
				}
				stack.add(sum);
			}
		}
		int answer = 0;
		while (!stack.isEmpty()) {
			int temp = stack.pop();
			answer += temp;
			// System.out.println(temp);
		}
		return answer;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		List<Character> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		String s = st.nextToken();
		for (int i = 0; i < n; i++) {
			list.add(s.charAt(i));
		}
		comb(list, -1);
		System.out.println(maxAnswer);
	}

	private static void comb(List<Character> list, int bracketIndex) {
//		for (int i = 0; i < list.size(); i++) {
//			System.out.print(list.get(i));
//		}

		int num = calc2(list);
		//System.out.println("= " + num);
		if (num > maxAnswer)
			maxAnswer = num;
		for (int i = bracketIndex + 1; i < list.size(); i++) {
			if (list.get(i) >= '0' && list.get(i) <= '9') {// 숫자면 괄호 추가 가능
				if (i + 4 < list.size()) {
					list.add(i, '(');
					list.add(i + 4, ')');
					comb(list, i + 4);
					list.remove(i + 4);
					list.remove(i);
				}
				if (i + 4 == list.size() + 1) {
					list.add(i, '(');
					list.add(')');
					comb(list, i + 4);
					list.remove(list.size() - 1);
					list.remove(i);
				}

			}
		}
	}
}