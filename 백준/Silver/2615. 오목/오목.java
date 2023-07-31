import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		//////////////////////////////////////////////////////////////
		// 테스트 후 아래 파일 입력을 표준입력으로 처리하는 문장은 주석 처리해주세요!!!! ( System.setIn처리 코드 )
		//////////////////////////////////////////////////////////////
		// System.setIn(new FileInputStream("Test5.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int ary[][] = new int[20][20];// 오목판
		// 입력 받기
		for (int i = 1; i < 20; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 20; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int deli[] = { 0, 1, 1, -1 };// 우, 하, 우하 ,상우만 확인하자
		int delj[] = { 1, 0, 1, 1 };

		int winner = 0;
		int wini = 0;
		int winj = 0;
		boolean foundWinner = false;
		for (int i = 1; i < 20; i++) {
			for (int j = 1; j < 20; j++) {

				for (int x = 0; x < 4; x++) {

					int depth = 1;
					int cnt = 1;
					while (true) {
						if (foundWinner)
							break;
						if (depth >= 5)
							break;
						int di = i + deli[x] * depth;
						int dj = j + delj[x] * depth;
						if (di >= 1 && di < 20 && dj >= 1 && dj < 20) {
							// 오목판 내부에 있고
							// 이전 바둑알과 같은 색이고
							// 빈공간이 아니면 한칸 더 본다
							if (ary[i][j] != 0 && ary[i][j] == ary[di][dj]) {
								depth++;
								cnt++;
							} else
								break;
						} else
							break;

						if (cnt == 5) {
							int color = ary[di][dj];
							int bi = i + deli[x] * (-1);
							int bj = j + delj[x] * (-1);
							int beforecolor = -1;
							if (bi >= 1 && bj >= 1 && bi < 20 && bj < 20) {
								beforecolor = ary[bi][bj];
							}
							di = i + deli[x] * depth;
							dj = j + delj[x] * depth;
							int sixcolor = -1;
							if (di >= 1 && di < 20 && dj >= 1 && dj < 20) {
								sixcolor = ary[di][dj];
							}
							if (color != sixcolor && color != beforecolor) {
								winner = color;
								wini = i;
								winj = j;
								foundWinner = true;

							}
							break;
						}

					}
					if (foundWinner)
						break;

				}
				if (foundWinner)
					break;

			}
			if (foundWinner)
				break;

		}
		if (!foundWinner) {
			System.out.println(0);
		} else {
			System.out.println(winner);
			System.out.println(wini + " " + winj);
		}

	}
}