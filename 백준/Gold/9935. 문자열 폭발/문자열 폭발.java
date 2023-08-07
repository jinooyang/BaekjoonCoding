import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String string = st.nextToken();
		st = new StringTokenizer(br.readLine());
		String sub = st.nextToken();

		StringBuilder sb = new StringBuilder();
		StringBuilder subSb = new StringBuilder();
		int len = string.length();
		int sublen = sub.length();

		for (int i = 0; i < sublen; i++) {
			subSb.append(sub.charAt(i));
		}
		for (int i = 0; i < len; i++) {
			sb.append(string.charAt(i));
			if (sb.length() >= sublen) {
				String temp = sb.substring(sb.length() - sublen);
				if (temp.equals(subSb.toString())) {
					sb.setLength(sb.length() - sublen);
				}
			}
		}

		if (sb.length() == 0) {
			System.out.println("FRULA");
		} else
			System.out.println(sb.toString());

	}
}