import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		String s = br.readLine();

		int size = s.length();

		boolean answer[] = new boolean[size];

		Arrays.fill(answer, true);

		for (int i = 0; i < n - 1; i++) {
			String temp = br.readLine();
			for (int l = 0; l < size; l++) {
				char c = temp.charAt(l);
				if (c != s.charAt(l))
					answer[l] = false;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<size;i++) {
			if(answer[i]) {
				sb.append(s.charAt(i));
			}
			else sb.append("?");
		}
		System.out.println(sb);

	}
}