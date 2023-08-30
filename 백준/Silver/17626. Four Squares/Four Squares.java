import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static Integer ary[];
	static boolean answerFound = false;
	static int answer = 5;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		ary = new Integer[225];
		for (int i = 224; i >= 0; i--) {
			ary[i] = i * i;
		}
		int history[] = new int[4];
		dfs(0, 0, 224, history);
		System.out.println(answer);
	}

	private static void dfs(int cnt, int sum, int idx, int[] history) {
		if (sum == n) {
			// System.out.println("cnt : " + history[0] + " " + history[1] + " " +
			// history[2] + " " + history[3]);
			// answerFound = true;
			if (cnt < answer)
				answer = cnt;
		//	answer = cnt;
		}
		if (cnt == 4) {
			return;
		}
		if (answerFound)
			return;
		if (sum > n)
			return;
		for (int i = idx; i >= 1; i--) {
			history[cnt] = i;
			dfs(cnt + 1, sum + ary[i], i, history);
			history[cnt] = 0;
			if (answerFound)
				return;
		}
	}
}