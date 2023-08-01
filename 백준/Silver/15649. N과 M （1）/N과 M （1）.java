import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N = 0;
	static int M = 0;
	static Stack<Integer> stack = new Stack<>();
	static List<String> answer = new ArrayList<>();
	static Set<Integer> set = new HashSet<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		printAll(0, 0);
		Collections.sort(answer);
		for (String s : answer) {
			System.out.println(s);
		}
	}

	private static void printAll(int peek, int m) {
		if (m == M) {
			Integer[] ary = stack.toArray(new Integer[0]);
			StringBuilder sb = new StringBuilder();
			for (Integer i : ary) {
				sb.append(i + " ");
			}
			answer.add(sb.toString());
			return;
		}
		for (int i = 1; i <= N; i++) {
			if (!set.contains(i)) {
				stack.add(i);
				set.add(i);
				printAll(i, m + 1);
				stack.pop();
				set.remove(i);
			}
		}

	}
}