import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int r1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());

			double A = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
			int answer = 2;// default
			if (A == (r1 + r2)) {// 바깥으로 인접했을 때
				answer = 1;
			} else if (A == 0 && (r1 == r2)) {// 같을때
				answer = -1;
			} else if ((A + r1) == r2) {// 안쪽으로 인접했을 때
				answer = 1;
			} else if ((A + r2) == r1) {// 안쪽으로 인접했을 때
				answer = 1;
			} else if (A > (r1 + r2)) { // 서로 외부에 있을 때
				answer = 0;
			} else if (r2 > A + r1 || r1 > A + r2) {
				answer = 0;

			}
			sb.append(answer).append("\n");

		}
		System.out.println(sb.toString());
	}
}