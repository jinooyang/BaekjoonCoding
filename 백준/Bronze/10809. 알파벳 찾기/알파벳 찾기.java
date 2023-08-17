import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String s = st.nextToken();
		int ary[] = new int[26];
		Arrays.fill(ary, -1);
		for (int i = 0; i < s.length(); i++) {
			int k = s.charAt(i) - 'a';
			if (ary[k] == -1)
				ary[k] = i;
		}
		for (int i = 0; i < 26; i++) {
			System.out.print(ary[i] + " ");
		}
	}
}