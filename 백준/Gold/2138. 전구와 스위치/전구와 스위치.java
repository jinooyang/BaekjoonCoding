import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		String s1 = st.nextToken();
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		String s2 = st2.nextToken();

		boolean startArray[] = new boolean[n];
		boolean zeroStartArray[] = new boolean[n];
		boolean endArray[] = new boolean[n];

		for (int i = 0; i < n; i++) {
			if (s1.charAt(i) == '0') {
				startArray[i] = false;
				zeroStartArray[i] = false;
			} else {
				startArray[i] = true;
				zeroStartArray[i] = true;

			}
			if (s2.charAt(i) == '0')
				endArray[i] = false;
			else
				endArray[i] = true;
		}
		int minCnt = 0;
		int zerominCnt = 1;
		// 0시작
		zeroStartArray[0] = !zeroStartArray[0];
		zeroStartArray[1] = !zeroStartArray[1];
		// 1시작

		for (int i = 1; i < n; i++) {
			if (!(zeroStartArray[i - 1] == endArray[i - 1])) {
				zerominCnt++;
				zeroStartArray[i - 1] = !zeroStartArray[i - 1];
				zeroStartArray[i] = !zeroStartArray[i];
				if (i < n - 1)
					zeroStartArray[i + 1] = !zeroStartArray[i + 1];
			}
			if (!(startArray[i - 1] == endArray[i - 1])) {
				minCnt++;
				startArray[i - 1] = !startArray[i - 1];
				startArray[i] = !startArray[i];
				if (i < n - 1)
					startArray[i + 1] = !startArray[i + 1];
			}
		}

		
		
		for (int i = 0; i < n; i++) {
			if (!(zeroStartArray[i] == endArray[i])) {
				// 같지 않으면 정답이 아님
				zerominCnt = Integer.MAX_VALUE;
			}
			if (!(startArray[i] == endArray[i])) {
				// 같지 않으면 정답이 아님
				minCnt = Integer.MAX_VALUE;
			}

		}

		int answer = Math.min(minCnt, zerominCnt);
		if (answer == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(answer);
	}
}