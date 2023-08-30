import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static Integer ary[];
	static int answer = 5;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		ary = new Integer[225];
		for (int i = 224; i >= 0; i--)
			ary[i] = i * i;
		dfs(0, 0, 224);
		System.out.println(answer);
	}

	private static void dfs(int cnt, int sum, int idx) {
		if (sum == n)
			if (cnt < answer)
				answer = cnt;
		if (cnt == 4)
			return;
		if (sum > n)
			return;
		for (int i = idx; i >= 1; i--)
			dfs(cnt + 1, sum + ary[i], i);

	}
}