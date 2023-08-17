import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer> list = new ArrayList<>();
	static int l;
	static StringBuilder answerSb = new StringBuilder();
	static int puzzleSize[][];
	static char puzzlePiece[][][];
	static boolean answerIsUpdated = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		puzzlePiece = new char[6][l][l];
		puzzleSize = new int[6][2];
		int count = 0;
		for (int x = 1; x <= 5; x++) {

			st = new StringTokenizer(br.readLine());
			puzzleSize[x][0] = Integer.parseInt(st.nextToken());
			puzzleSize[x][1] = Integer.parseInt(st.nextToken());
			for (int i = 0; i < puzzleSize[x][0]; i++) {
				st = new StringTokenizer(br.readLine());
				String s = st.nextToken();
				for (int j = 0; j < puzzleSize[x][1]; j++) {
					puzzlePiece[x][i][j] = s.charAt(j);
					if (puzzlePiece[x][i][j] == '#')
						count++;
				}
			}
		}

		for (int i = 0; i < l; i++)
			for (int j = 0; j < l; j++)
				answerSb.append(6);
		// input 완료
		// 순열을 만들어서 순서대로 새로운 보드에 찍어보자
		if (count == l * l)
			comb(0, 0);
		if (!answerIsUpdated)
			System.out.println("gg");
		else {
			String answer = answerSb.toString();
			for (int i = 0; i < l; i++) {
				for (int j = 0; j < l; j++) {
					System.out.print(answer.charAt(i * l + j));
				}
				System.out.println();
			}
		}
	}

	private static void comb(int cnt, int used) {// used에는 사용된 숫자가 저장
		if (cnt == 5) {
			// 새로운 그래프를 만들어서 리스트 순서대로 찍자
			// 순열 생성 완료
			// 새로운 보드 생성
			int testAry[][] = new int[l][l];
			boolean result = true;// 보드에 성공적으로 모두 붙였나
			for (int i = 0; i < 5; i++) {
				int pnum = list.get(i);// 이번에 붙일 퍼즐 번호
				result = addPuzzleToBoard(testAry, pnum);// 붙이고 성공하면 true, 아니면 false
				if (!result)// 실패했으면 브레이크
					break;
			}
			if (result) {// 모두 붙이기 성공했을 경우
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < l; i++) {
					for (int j = 0; j < l; j++) {
						sb.append(testAry[i][j]);
					}
				}
				if (answerSb.toString().compareTo(sb.toString()) > 0) {
					answerIsUpdated = true;
					answerSb = sb;
				}
			}
			return;
		}
		for (int i = 1; i <= 5; i++) {
			if ((used & 1 << i) == 0) {
				list.add(i);
				comb(cnt + 1, used | (1 << i));
				list.remove(list.size() - 1);
			}
		}
	}

	private static boolean addPuzzleToBoard(int[][] testAry, int pnum) {
		// System.out.println(pnum + " : " + " n : " + puzzleSize[pnum][0] + ", m : " +
		// puzzleSize[pnum][1]);

		boolean added = false;
		for (int i = 0; i < l - puzzleSize[pnum][0] + 1; i++) {
			for (int j = 0; j < l - puzzleSize[pnum][1] + 1; j++) {
				// 각 좌표에서 되는지 확인한다
				boolean canAdd = true;
				for (int y = 0; y < puzzleSize[pnum][0]; y++) {
					for (int x = 0; x < puzzleSize[pnum][1]; x++) {

						if (puzzlePiece[pnum][y][x] == '#')
							if (testAry[y + i][x + j] != 0)
								canAdd = false;
					}
				}
				// 더할 수 있으면 더하고 그만 확인한다
				if (canAdd) {
					for (int y = 0; y < puzzleSize[pnum][0]; y++) {
						for (int x = 0; x < puzzleSize[pnum][1]; x++) {

							if (testAry[y + i][x + j] == 0 && puzzlePiece[pnum][y][x] == '#') {
								testAry[y + i][x + j] = pnum;
							}

						}
					}
					added = true;
					break;
				}
			}
			if (added)// 더 해졌으면 그만 확인한다
				break;
		}
//		if (added) {//잘 더해졌는지 확인
//			for (int i = 0; i < l; i++) {
//				for (int j = 0; j < l; j++) {
//					System.out.print(testAry[i][j]);
//				}
//				System.out.println();
//			}
//		}
		return added;
	}
}