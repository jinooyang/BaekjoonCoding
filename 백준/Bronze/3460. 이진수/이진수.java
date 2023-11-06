import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for (int test = 0; test < t; test++) {
			st = new StringTokenizer(br.readLine());
			Integer n = Integer.parseInt(st.nextToken());
			String s = Integer.toBinaryString(n);
			//System.out.println(s);
			for (int i = 0; i < s.length(); i++) {
				if(s.charAt(s.length()-1-i)=='1')
					sb.append(i).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
