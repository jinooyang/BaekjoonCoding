import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int answer = 0;
	static List<Integer> list = new ArrayList<>();
	static int n;
	static int ary[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		ary = new int[n][n];

		int deli[] = { -1, 1, 0, 0 };// 0:상 1:하 2:좌 3:우
		int delj[] = { 0, 0, -1, 1 };

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 최대 5번 이동
		// 상하좌우 중복 순열을 구한다?
		// 한번 합쳐진 블록은 또 합쳐질 수 없다***
		perm(0);
		System.out.println(answer);

	}

	// 이동 시킬 순서를 정한다
	private static void perm(int selected) {
		if (selected == 5) {
			// 계산해본다
			int temp[][] = new int[n][n];// 이동할 임시 배열 복사한다
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					temp[i][j] = ary[i][j];
				}
			}
			for (int i = 0; i < 5; i++) {
				int direction = list.get(i);
				move(direction, temp);// 해당 방향으로 이동한다
			}
			int max = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (temp[i][j] > max)
						max = temp[i][j];
				}
			}
			if (max > answer)
				answer = max;
			return;
		}
		for (int i = 0; i < 4; i++) {

			list.add(i);
			perm(selected + 1);
			list.remove(list.size() - 1);

		}
	}

	// 0:상 1:하 2:좌 3:우
	private static void move(int direction, int[][] temp) {
		if (direction == 0) {
			rotateUpsideDown(temp);
			moveDown(temp);
		}
		if (direction == 1) {

			moveDown(temp);
		}
		if (direction == 2) {
			rotateLeft(temp);
			moveDown(temp);
			rotateRight(temp);
		}
		if (direction == 3) {
			rotateRight(temp);
			moveDown(temp);
			rotateLeft(temp);
		}

	}

	private static void moveDown(int[][] temp) {
		boolean fixed[][] = new boolean[n][n];
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j < n; j++) {
				int nowi = i;
				while (true) {
					// System.out.println("i :" + i + ", nowi : " + nowi);
					if (nowi + 1 >= n)// 범위를 벗어나면 브레이크
						break;
					if (temp[nowi][j] == 0)
						break;
					if (fixed[nowi + 1][j] == true)
						break;
					if (temp[nowi + 1][j] == 0) {// 다음이 빈 공간이면 내려가자
						temp[nowi + 1][j] = temp[nowi][j];
						temp[nowi][j] = 0;
						nowi++;
						continue;
					}
					if (temp[nowi + 1][j] == temp[nowi][j]) {
						// 같으면 합치자..위에서 무조건 false인거 확인함
						temp[nowi + 1][j] += temp[nowi + 1][j];
						fixed[nowi + 1][j] = true;
						temp[nowi][j] = 0;
						break;
					}
					break;

				}
			}
		}
	}

	private static void rotateRight(int[][] x) {
		int temp[][] = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				temp[i][j] = x[n - 1 - j][i];
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				x[i][j] = temp[i][j];
			}
		}

	}

	private static void rotateLeft(int[][] x) {
		int temp[][] = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				temp[i][j] = x[j][n - 1 - i];
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				x[i][j] = temp[i][j];
			}
		}
	}

	private static void rotateUpsideDown(int[][] x) {
		rotateRight(x);
		rotateRight(x);
	}

}