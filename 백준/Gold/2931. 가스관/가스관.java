import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int i;
		int j;
		int dir;

		public Node(int i, int j, int dir) {
			super();
			this.i = i;
			this.j = j;
			this.dir = dir;
		}

	}

	static char toDirection[][] = { { '|', '+', '1', '4' }, { '|', '+', '2', '3' }, { '-', '+', '1', '2' },
			{ '-', '+', '3', '4' } };// 상하좌우
	static int deli[] = { -1, 1, 0, 0 };
	static int delj[] = { 0, 0, -1, 1 };
	static char ary[][];
	static int N;
	static int M;
	static int ai;
	static int aj;
	static char ac;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());// 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ary = new char[N][M];
		Node startNode = null;
		Node endNode = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < M; j++) {
				ary[i][j] = s.charAt(j);
				if (ary[i][j] == 'M')
					startNode = new Node(i, j, -1);
				if (ary[i][j] == 'Z')
					endNode = new Node(i, j, -1);
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (ary[i][j] == '.')
					if (check(i, j)) {
						System.out.println((ai + 1) + " " + (aj + 1) + " " + ac);
						return;
					}

			}
		}

	}

	private static boolean check(int i, int j) {
		// 현재 좌표의 상하좌우를 확인한다
		int find = 0;
		boolean answer = false;
		for (int idx = 0; idx < 4; idx++) {
			int di = i + deli[idx];
			int dj = j + delj[idx];
			if (di >= 0 && dj >= 0 && di < N && dj < M) {
				char c = ary[di][dj];
				if (idx == 0) {// 상이 뚫려있음
					if (c == '|' || c == '+' || c == '1' || c == '4') {
						find += 1000;
					}
				}
				if (idx == 1) {
					if (c == '|' || c == '+' || c == '2' || c == '3') {
						find += 100;
					}
				}
				if (idx == 2) {
					if (c == '-' || c == '+' || c == '1' || c == '2') {
						find += 10;
					}
				}
				if (idx == 3) {
					if (c == '-' || c == '+' || c == '3' || c == '4') {
						find += 1;
					}
				}

			}
		}

		if (find == 1111) {
			answer = true;
			ai = i;
			aj = j;
			ac = '+';
		}
		if (find == 1100) {
			answer = true;
			ai = i;
			aj = j;
			ac = '|';
		}
		if (find == 11) {
			answer = true;
			ai = i;
			aj = j;
			ac = '-';
		}
		if (find == 101) {// 하,우
			answer = true;
			ai = i;
			aj = j;
			ac = '1';
		}
		if (find == 1001) {// 상우
			answer = true;
			ai = i;
			aj = j;
			ac = '2';
		}
		if (find == 1010) {// 좌상
			answer = true;
			ai = i;
			aj = j;
			ac = '3';
		}
		if (find == 110) {// 좌 하
			answer = true;
			ai = i;
			aj = j;
			ac = '4';
		}

		return answer;
	}

}