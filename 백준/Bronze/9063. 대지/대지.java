import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int minX = 10000;
		int minY = 10000;
		int maxX = -10000;
		int maxY = -10000;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			minX = Math.min(x, minX);
			maxX = Math.max(x, maxX);

			minY = Math.min(y, minY);
			maxY = Math.max(y, maxY);
		}
		int answer = (maxY - minY) * (maxX - minX);
		System.out.println(answer);
	}
}