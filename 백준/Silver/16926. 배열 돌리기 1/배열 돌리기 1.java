import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int ary[][] = new int[n][m];
		int answer[][] = new int[n][m];
		int deli[] = { 0, 1, 0, -1 };// 좌,하,우,상
		int delj[] = { -1, 0, 1, 0 };
		// 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 계산
		int insideLayer = Math.min(n, m);
		insideLayer = insideLayer / 2 - 1;// 가장 안쪽 레이어
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int answeri = i;
				int answerj = j;
				int direction = 0;
				int layer = Math.min(n - 1 - i, i);
				layer = Math.min(layer, m - 1 - j);
				layer = Math.min(layer, j);
				if (i == n - 1 - layer || j == m - 1 - layer)
					direction = 2;
				for (int rotate = 0; rotate < r; rotate++) {
					int di = answeri + deli[direction];
					int dj = answerj + delj[direction];
					// layer안쪽인지
					if (di >= layer && di < n - layer && dj >= layer && dj < m - layer) {
						// 제일 안쪽 layer인 경우
						if (layer == insideLayer) {
							answeri = di;
							answerj = dj;
							continue;
						}
						// 안쪽 layer의 바깥인지
						if (!(di >= layer + 1 && di < n - layer - 1 && dj >= layer + 1 && dj < m - layer - 1)) {
							// 진행 시켜
							answeri = di;
							answerj = dj;
							continue;
						}
						// 방향 전환
						else {
							direction = (direction + 1) % 4;
							rotate--;
							continue;
						}
					}
					// 방향 전환
					else {
						direction = (direction + 1) % 4;
						rotate--;
						continue;
					}

				}
				answer[answeri][answerj] = ary[i][j];
			}
		}

		// 출력
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				bw.write(answer[i][j] + " ");
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}

}