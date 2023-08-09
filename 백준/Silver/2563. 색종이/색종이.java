import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		boolean ary[][] = new boolean[100][100];
		int answer = 0;
		for (int in = 0; in < n; in++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			// 종이를 붙인 영역을 boolean ary에 저장한다
			// false -> true로 바꾸는 횟수가 정답
			for (int i = y; i < y + 10; i++) {
				for (int j = x; j < x + 10; j++) {
					if (!ary[i][j]) {
						answer++;
						ary[i][j] = true;
					}
				}
			}
		}
		System.out.println(answer);
	}
}