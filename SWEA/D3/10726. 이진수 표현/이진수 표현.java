import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < tc; t++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int temp = (1 << x) - 1;
		

			if ((y & temp) == temp)
				sb.append("#").append(t + 1).append(" ").append("ON").append("\n");
			else
				sb.append("#").append(t + 1).append(" ").append("OFF").append("\n");
		}
		System.out.println(sb);
	}
}