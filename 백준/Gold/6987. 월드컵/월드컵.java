import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int noneDrawGames = 15;
	static int inputAry[][];
	static boolean answerFound = false;
	static int teamsInGame[][];
	static int possibilities[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		inputAry = new int[6][3];
		teamsInGame = new int[15][2];
		int idx = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = i + 1; j < 6; j++) {
				teamsInGame[idx][0] = i;
				teamsInGame[idx][1] = j;
				idx++;
			}
		}

		for (int tc = 0; tc < 4; tc++) {
			answerFound = false;
			possibilities = new int[6][3];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					inputAry[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			play(0);
			if (!answerFound)
				System.out.print(0 + " ");
		}
	}

	// 모든 경기의 경우의 수를 생성하자
	private static void play(int gamePlayed) {
		// 정답을 찾았으면 리턴
		if (answerFound)
			return;
		// 정답인지 확인하기
		if (gamePlayed == 15) {
			boolean canBeAnswer = true;
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					if (possibilities[i][j] != inputAry[i][j]) {
						canBeAnswer = false;
						break;
					}
				}
				if (!canBeAnswer)
					break;
			}
			if (canBeAnswer) {
				System.out.print(1 + " ");
				answerFound = true;
			}
			return;
		}

		int one = teamsInGame[gamePlayed][0];
		int two = teamsInGame[gamePlayed][1];
		// 경기
		// 1team win
		if (possibilities[one][0] + 1 <= inputAry[one][0] && possibilities[two][2] + 1 <= inputAry[two][2]) {
			//System.out.println(one + "팀 승리, " + two + "팀 패배");
			possibilities[one][0]++;
			possibilities[two][2]++;
			play(gamePlayed + 1);
			possibilities[one][0]--;
			possibilities[two][2]--;
		}

		// 2team win
		if (possibilities[two][0] + 1 <= inputAry[two][0] && possibilities[one][2] + 1 <= inputAry[one][2]) {
			//System.out.println(two + "팀 승리, " + one + "팀 패배");

			possibilities[two][0]++;
			possibilities[one][2]++;
			play(gamePlayed + 1);
			possibilities[two][0]--;
			possibilities[one][2]--;
		}
		// draw

		if (possibilities[two][1] + 1 <= inputAry[two][1] && possibilities[one][1] + 1 <= inputAry[one][1]) {
			//System.out.println(two + "팀 , " + one + "팀 무승부");
			possibilities[two][1]++;
			possibilities[one][1]++;
			play(gamePlayed + 1);
			possibilities[two][1]--;
			possibilities[one][1]--;
		}

	}
}