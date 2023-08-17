import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		for (int i = 0; i < tc; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			int answer = 0;
			int cnt = 1;
			for (int x = 0; x < s.length(); x++) {
				if (s.charAt(x) == 'O')
					answer += cnt++;
				else cnt = 1;
			}
			System.out.println(answer);
		}
	}
}