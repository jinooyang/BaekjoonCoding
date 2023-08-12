import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int player[][];
	static int maxScore = 0;
	static int inning;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		inning = Integer.parseInt(st.nextToken());
		player = new int[9][inning];
		for (int in = 0; in < inning; in++) {
			st = new StringTokenizer(br.readLine());
			for (int playerNumber = 0; playerNumber < 9; playerNumber++) {
				player[playerNumber][in] = Integer.parseInt(st.nextToken());
			}
		}

		int order[] = new int[9];
		findCombo(order, 0, 0);
		System.out.println(maxScore);
	}

	// 선수의 순서를 모두 구하자
	private static void findCombo(int[] order, int cnt, int used) {
		if (cnt == 9) {
			int score = play(order);
			if (score > maxScore)
				maxScore = score;
		}
		if (cnt == 3) {// 1번선수는 4번타자로 고정
			order[cnt] = 0;
			findCombo(order, cnt + 1, used | (1 << 0));
		} else {
			for (int i = 1; i < 9; i++) {
				if ((used & 1 << i) == 0) {
					order[cnt] = i;
					findCombo(order, cnt + 1, used | (1 << i));
				}
			}
		}
	}

	private static int play(int[] order) {
		boolean base[] = new boolean[3];// 베이스에 선수가 있는지 확인하자
		int turn = 0;// 타자 순서
		int outCount = 0;
		int score = 0;
		for (int i = 0; i < inning; i++) {
			while (true) {
				if (outCount == 3) {
					outCount = 0;// 아웃을 초기화한다
					Arrays.fill(base, false);
					break;// 이닝이 끝난다
				}
				// 점수 계산하기
				int thisTurnResult = player[order[turn]][i];// 이번 선수가 현재 이닝에 얻은 결과
				if (thisTurnResult == 0) {// 아웃
					outCount++;
				}
				if (thisTurnResult == 1) {// 안타
					if (base[2]) {
						score++;
					}
					base[2] = base[1];
					base[1] = base[0];
					base[0] = true;
				}
				if (thisTurnResult == 2) {// 2루타

					if (base[2]) {
						score++;
					}
					if (base[1]) {
						score++;
					}
					base[2] = base[0];
					base[1] = true;
					base[0] = false;
				}
				if (thisTurnResult == 3) {// 3루타
					if (base[2]) {
						score++;
					}
					if (base[1]) {
						score++;
					}
					if (base[0]) {
						score++;
					}
					base[2] = true;
					base[1] = false;
					base[0] = false;
				}
				if (thisTurnResult == 4) {// 홈런
					if (base[2]) {
						score++;
					}
					if (base[1]) {
						score++;
					}
					if (base[0]) {
						score++;
					}
					score++;
					Arrays.fill(base, false);
				}

				turn = (turn + 1) % 9;// 다음 타자 올리기
			}
		}

		return score;
	}
}