import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long ary[][] = new long[8][8];

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		for (int i = 0; i < 8; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 8; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		char flag = st.nextToken().charAt(0);
		play(flag);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				// System.out.print(Math.abs(ary[i][j]) + " ");
				sb.append(Math.abs(ary[i][j])).append(" ");
			}
			sb.append("\n");
			// System.out.println();
		}
		System.out.println(sb);

	}

	private static void play(char flag) {
		if (flag == 'U')
			moveUp();
		if (flag == 'D')
			moveDown();
		if (flag == 'L')
			moveLeft();
		if (flag == 'R')
			moveRight();

	}

	private static void moveUp() {
		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				if (ary[i][j] == 0)
					continue;
				if (i == 0)
					continue;
				int nowi = i;
				while (nowi > 0) {
					//0이면 그냥 움직인다
					if (ary[nowi - 1][j] == 0) {
						ary[nowi - 1][j] = ary[nowi][j];
						ary[nowi][j] = 0;
						nowi--;
						continue;
					}
					if (ary[nowi - 1][j] == ary[nowi][j] && ary[nowi][j] > 0) {
						ary[nowi - 1][j] *= -2;
						ary[nowi][j] = 0;
						nowi--;
						continue;
					}
					break;

				}
			}
		}
	}

	private static void moveDown() {
		for (int j = 0; j < 8; j++) {
			for (int i = 7; i >= 0; i--) {
				if (ary[i][j] == 0)
					continue;
				if (i == 7)
					continue;
				int nowi = i;
				while (nowi < 7) {
					//0이면 그냥 움직인다
					if (ary[nowi + 1][j] == 0) {
						ary[nowi + 1][j] = ary[nowi][j];
						ary[nowi][j] = 0;
						nowi++;
						continue;
					}
					if (ary[nowi + 1][j] == ary[nowi][j] && ary[nowi][j] > 0) {
						ary[nowi + 1][j] *= -2;
						ary[nowi][j] = 0;
						nowi++;
						continue;
					}
					break;

				}
			}
		}
	}

	private static void moveLeft() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (ary[i][j] == 0)
					continue;
				if (j == 0)
					continue;
				int nowj = j;
				while (nowj > 0) {
					//0이면 그냥 움직인다
					if (ary[i][nowj - 1] == 0) {
						ary[i][nowj - 1] = ary[i][nowj];
						ary[i][nowj] = 0;
						nowj--;
						continue;
					}
					if (ary[i][nowj - 1] == ary[i][nowj] && ary[i][nowj] > 0) {
						ary[i][nowj - 1] *= -2;
						ary[i][nowj] = 0;
						nowj--;
						continue;
					}
					break;

				}
			}
		}
	}

	private static void moveRight() {
		for (int i = 0; i < 8; i++) {
			for (int j = 7; j >= 0; j--) {
				if (ary[i][j] == 0)
					continue;
				if (j == 7)
					continue;
				int nowj = j;
				while (nowj < 7) {
					//0이면 그냥 움직인다
					if (ary[i][nowj + 1] == 0) {
						ary[i][nowj + 1] = ary[i][nowj];
						ary[i][nowj] = 0;
						nowj++;
						continue;
					}
					if (ary[i][nowj + 1] == ary[i][nowj] && ary[i][nowj] > 0) {
						ary[i][nowj + 1] *= -2;
						ary[i][nowj] = 0;
						nowj++;
						continue;
					}
					break;

				}
			}
		}

	}
}
