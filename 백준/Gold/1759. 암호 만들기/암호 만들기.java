import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int l;
	static int c;
	static char ary[];
	static StringBuilder answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		answer = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		ary = new char[c];

		for (int i = 0; i < c; i++) {
			ary[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(ary);
		StringBuilder sb = new StringBuilder();
		getCombo(0, -1, sb, false, 0);
		System.out.println(answer.toString());
	}

	private static void getCombo(int cnt, int before, StringBuilder sb, boolean aeiou, int abcCount) {
		if (cnt == l) {
			if (aeiou && abcCount >= 2)
				answer.append(sb).append("\n");
			return;
		}
		for (int i = before + 1; i < c; i++) {
			sb.append(ary[i]);
			if (ary[i] == 'a' || ary[i] == 'e' || ary[i] == 'i' || ary[i] == 'o' || ary[i] == 'u')
				getCombo(cnt + 1, i, sb, true, abcCount);
			else
				getCombo(cnt + 1, i, sb, aeiou, abcCount + 1);
			sb.replace(cnt, cnt + 1, "");
		}
	}
}