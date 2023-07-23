import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean answer = true;

		boolean chess[][] = new boolean[6][6];

		int starti = 0;
		int startj = 0;

		int beforei = 0;
		int beforej = 0;
		for (int x = 0; x < 36; x++) {

			String cur = sc.next();
			int j = cur.charAt(0) - 'A';

			int i = cur.charAt(1) - '1';

			if (x == 0) {
				starti = i;
				startj = j;
				beforei = i;
				beforej = j;
				// System.out.println("start i : " + starti + ", startj : " + startj);
			}

			if (x != 0) {
				// 이전 지점에서 여기로 올 수 있는지 확인하기
				int di = Math.abs(beforei - i);
				int dj = Math.abs(beforej - j);
				if (!((di == 2 && dj == 1) || (dj == 2 && di == 1))) {
					//System.out.println("여기로 못옴!");
					answer = false;
					break;
				}
				beforei = i;
				beforej = j;
			}

			if (!chess[i][j])
				chess[i][j] = true;
			else {
				// System.out.println(2);
				answer = false;
				break;

			}

			if (x == 35) {
				// System.out.println("last : " + i + " " + j);
				// 처음으로 돌아갈 수 있는 지 확인하기
				// ur,ul, ru,rd, dr,dl, lu,ld
				boolean found = false;
				int deli[] = { -2, -2, -1, 1, 2, 2, 1, -1 };
				int delj[] = { 1, -1, 2, 2, 1, -1, -2, -2 };
				for (int idx = 0; idx < 8; idx++) {
					int di = i + deli[idx];
					int dj = j + delj[idx];

					if (di == starti && dj == startj) {
						found = true;
					}
				}
				if (found != true) {
					// System.out.println(1);
					answer = false;
				}
			}
		}

//		for (int i = 0; i < 6; i++) {
//			for (int j = 0; j < 6; j++) {
//
//				System.out.print(chess[i][j]);
//			}
//			System.out.println();
//		}

		if (answer) {
			System.out.println("Valid");
		} else {
			System.out.println("Invalid");
		}

	}
}