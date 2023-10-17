import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int ary[][];
	static int totalcnt = 0;

	static class Node {
		int i;
		int j;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

	}

	static List<Node> list = new ArrayList<>();// 0의 위치를 기록한다
	static Set<Integer>[] across = new HashSet[9];// 가로에 1~9개 있는지 확인한다
	static Set<Integer>[] down = new HashSet[9];// 가로에 1+9있는지 확인한다
	static boolean answerFound = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		ary = new int[9][9];
		for (int i = 0; i < 9; i++) {
			across[i] = new HashSet<>();
			down[i] = new HashSet<>();
		}
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
//			String s = st.nextToken();
			for (int j = 0; j < 9; j++) {
//				ary[i][j] = s.charAt(j) - '0';
				ary[i][j] = Integer.parseInt(st.nextToken());
				if (ary[i][j] == 0) {
					totalcnt++;
					list.add(new Node(i, j));
				} else {
					across[i].add(ary[i][j]);
					down[j].add(ary[i][j]);
				}
			}
		}
		// for (int i = 0; i < 9; i++)
		// System.out.println(down[i]);
		// 재귀 완탐으로 푼다
	//	System.out.println(list.size());
		sudoku(-1);
	}

	private static void sudoku(int beforeUsed) {
		// System.out.println("K");
//		System.out.println("-------------");
//		for (int i = 0; i < 9; i++) {
//			for (int j = 0; j < 9; j++) {
//				System.out.print(ary[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println("-------------");
		if (beforeUsed == list.size()-1) {
			// 정답을 출력하고 프로그램을 종료한다
			// 작은 숫자부터 넣었으니 먼저 나오는게 정답이다
//			System.out.println("found");
			for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(ary[i][j]+" ");
			}
			System.out.println();
		}
			// System.exit(0);
			answerFound = true;
			return;
		}
		if (answerFound)
			return;
		Node node = list.get(beforeUsed + 1);
		int i = node.i;
		int j = node.j;
		// 현재 여기에 1부터 9까지 넣어본다
		for (int x = 1; x <= 9; x++) {

			if (across[i].contains(x))// 가로에 이미 이 값이 있어
				continue;
			if (down[j].contains(x))// 세로에 이미 이 값이 있어
				continue;

			// 넣을수 있는 값이니까 넣어보자
			ary[i][j] = x;

			if (check(i, j) == true) {
				across[i].add(x);
				down[j].add(x);
				sudoku(beforeUsed + 1);
				across[i].remove(x);
				down[j].remove(x);
			}
			ary[i][j] = 0;
		}
	}

	// 현재 스도쿠 맵이 유효한지 판단한다
	// 현재 사각형에 1~9 중복이 없는지 보자
	private static boolean check(int x, int y) {
		int si = x / 3;
		int sj = y / 3;
		si *= 3;
		sj *= 3;
		boolean cnt[] = new boolean[10];
		for (int i = si; i < si + 3; i++) {
			for (int j = sj; j < sj + 3; j++) {
				int now = ary[i][j];
				if (now == 0)
					continue;
				if (cnt[now] == true)
					return false;
				cnt[now] = true;
			}
		}

		return true;
	}
}