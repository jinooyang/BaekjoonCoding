import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int cnt = 0;
	static String ans = "";
	static int n;

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		String next = null;
		StringBuilder sb = new StringBuilder();
		while ((next = br.readLine()) != null) {
			ans = "";
			cnt = 0;
			st = new StringTokenizer(next);
			String s = st.nextToken();
			n = Integer.parseInt(st.nextToken());
			char[] ary = s.toCharArray();
			char[] perm = new char[s.length()];
			getPermutation(ary, 0, perm, -1);
			if (ans.equals(""))
				ans = "No permutation";
			sb.append(s).append(" ").append(n).append(" = ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	private static void getPermutation(char[] ary, int bit, char[] perm, int before) {
		if (cnt == n)
			return;
		int len = ary.length;
		if (before == len - 1) {
			cnt++;
			if (cnt == n) {
				permToString(perm);
			}
			return;
		}

		for (int i = 0; i < len; i++) {
			if (((1 << i) & bit) == 0) {
				perm[before + 1] = ary[i];
				getPermutation(ary, bit | (1 << i), perm, before + 1);
				if (cnt == n)
					break;
				continue;
			} else {
				continue;
			}
		}

	}

	private static void permToString(char[] perm) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < perm.length; i++) {
			sb.append(perm[i]);
		}
		ans = sb.toString();
	}
}