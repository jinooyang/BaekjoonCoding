import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String text = br.readLine();
		String pattern = br.readLine();
		int n = pattern.length();
		int table[] = new int[n];

		int idx = 0;

		for (int i = 1; i < n; i++) {
			while (idx > 0 && pattern.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx - 1];
			}
			if (pattern.charAt(i) == pattern.charAt(idx)) {
				idx++;
				table[i] = idx;
			}
		}

		int m = text.length();
		int begin = 0;
		int match = 0;
		int answer = 0;
		StringBuilder sb = new StringBuilder();
		while (begin <= m - n) {
			if (match < n && text.charAt(begin + match) == pattern.charAt(match)) {
				match++;
				if (match == n) {
					answer++;
					sb.append(begin + 1).append("\n");
				}
			} else {
				if (match == 0)
					begin++;
				else {
					begin += match - table[match - 1];
					match = table[match - 1];
				}
			}

		}
		System.out.println(answer);
		System.out.print(sb);

	}
}