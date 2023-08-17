import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static char ary[][];
	static int N;
	static int M;
	static int currentDirection;
	static int nowi;
	static int nowj;
	static int deli[] = { -1, 1, 0, 0 };// 상하좌우
	static int delj[] = { 0, 0, -1, 1 };

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		// int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			ary = new char[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				String s = st.nextToken();
				for (int j = 0; j < M; j++) {
					ary[i][j] = s.charAt(j);

					if (ary[i][j] == '<')
						currentDirection = 2;
					if (ary[i][j] == '>')
						currentDirection = 3;
					if (ary[i][j] == '^')
						currentDirection = 0;
					if (ary[i][j] == 'v')
						currentDirection = 1;
					if (ary[i][j] == '<' || ary[i][j] == '>' || ary[i][j] == '^' || ary[i][j] == 'v') {
						nowi = i;
						nowj = j;
						ary[i][j] = '.';
					}
				}
			}
			st = new StringTokenizer(br.readLine());
			int cnum = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			String allInputs = st.nextToken();
			int inputAry[] = new int[cnum];
			for (int i = 0; i < cnum; i++) {
				char input = allInputs.charAt(i);
				if (input == 'U')
					inputAry[i] = 0;
				if (input == 'D')
					inputAry[i] = 1;
				if (input == 'L')
					inputAry[i] = 2;
				if (input == 'R')
					inputAry[i] = 3;
				if (input == 'S')
					inputAry[i] = 4;
			}
			// 입력 완료!
			// 게임 시작
			for (int c = 0; c < cnum; c++) {
				if (0 <= inputAry[c] && inputAry[c] <= 3) {
					move(inputAry[c]);
				} else
					shoot();
			}

			// 결과 출력
			System.out.print("#" + test_case + " ");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (i == nowi && j == nowj) {
						char c = '.';
						if (currentDirection == 0)
							c = '^';
						if (currentDirection == 1)
							c = 'v';
						if (currentDirection == 2)
							c = '<';
						if (currentDirection == 3)
							c = '>';
						System.out.print(c);
					} else
						System.out.print(ary[i][j]);
				}
				System.out.println();
			}
		}
	}

	private static void shoot() {
		int dir = currentDirection;
		int starti = nowi;
		int startj = nowj;
		while (true) {
			int di = starti + deli[dir];
			int dj = startj + delj[dir];
			if (di >= 0 && dj >= 0 && di < N && dj < M) {// 맵 안쪽이라면
				if (ary[di][dj] == '.' || ary[di][dj] == '-') {// 평지라면
					starti = di;
					startj = dj;
					continue;
				}
				if (ary[di][dj] == '#') {// 철벽이라면
					break;
				}
				if (ary[di][dj] == '*') {// 벽돌 벽이라면
					ary[di][dj] = '.';// 벽을 부순다
					break;
				}
			} else {// 맵 밖이라면
				break;
			}
		}
	}

	private static void move(int dir) {
		int di = nowi + deli[dir];
		int dj = nowj + delj[dir];
		if (di >= 0 && dj >= 0 && di < N && dj < M && ary[di][dj] == '.') {// 평지로만 이동할 수 있다
			nowi = di;
			nowj = dj;

		}
		currentDirection = dir;
	}
}