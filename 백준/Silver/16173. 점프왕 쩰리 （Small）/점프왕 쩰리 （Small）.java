import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// 제일 많은 곳에서 한명씩 델고 간다
		Scanner sc = new Scanner(System.in);

		// 후보 수
		int n = sc.nextInt();
		int ary[][] = new int[n][n];
		boolean chk[][] = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ary[i][j] = sc.nextInt();
			}
		}

		Queue<Integer> qx = new LinkedList<>();
		Queue<Integer> qy = new LinkedList<>();

		qx.add(0);
		qy.add(0);
		chk[0][0] = true;
		int deltai[] = { 0, 1 };//우 , 하
		int deltaj[] = { 1, 0 };
		boolean canGo = false;

		while (!qx.isEmpty()) {

			int i = qx.poll();
			int j = qy.poll();


			if (ary[i][j] == -1) {
				canGo = true;
				break;
			}
			for (int x = 0; x < 2; x++) {

				int move = ary[i][j];

				int di = i + (deltai[x] * move);
				int dj = j + (deltaj[x] * move);

				if (di >= 0 && dj >= 0 && di < n && dj < n && chk[di][dj]==false) {
					// 범위 내에 있다면 큐에 넣기
					qx.add(di);
					qy.add(dj);
					chk[di][dj] = true;
				}

			}

		}

		if (canGo) {
			System.out.println("HaruHaru");
		} else
			System.out.println("Hing");

	}
}