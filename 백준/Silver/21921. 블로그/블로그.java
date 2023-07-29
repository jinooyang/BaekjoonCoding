import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());// 블로그 운영 기간
		int x = Integer.parseInt(st.nextToken());// x일동안 방문자 수

		int aryN[] = new int[n + x];
		int aryX[] = new int[n + x];
		int maxVisit = 0;
		int maxCnt = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = x; i < n+x; i++) {
			aryN[i] = Integer.parseInt(st.nextToken());
			aryX[i] = aryN[i] + aryX[i - 1];//누적합
			//System.err.println(aryX[i]);
			if (aryX[i] - aryX[i-x] > maxVisit) {
				maxVisit = aryX[i] - aryX[i-x];
				maxCnt = 1;
			} else if (aryX[i] - aryX[i-x] == maxVisit) {
				maxCnt++;
			}
		}
		if (maxVisit == 0) {
			System.out.println("SAD");
		} else {
			System.out.println(maxVisit);
			System.out.println(maxCnt);
		}

	}
}