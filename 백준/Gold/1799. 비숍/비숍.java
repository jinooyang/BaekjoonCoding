import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int i;
		int j;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + i;
			result = prime * result + j;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (i != other.i)
				return false;
			if (j != other.j)
				return false;
			return true;
		}

	}

	static List<Node> whitelist = new ArrayList<>();
	static List<Node> blacklist = new ArrayList<>();
	static int whitesize;
	static int blacksize;
	static int whiteMaxAnswer = 0;
	static int blackMaxAnswer = 0;
	static int ary[][];
	static int N;
	static int deli[] = { 1, 1 };// 대각선 탐방을 한다
	static int delj[] = { 1, -1 };// 아래로만 확인해도 괜찮다

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		ary = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int x = Integer.parseInt(st.nextToken());
				if (x == 1) {// 놓을 수 있는 위치
					ary[i][j] = 0;
					if ((i + j) % 2 == 0)
						whitelist.add(new Node(i, j));// 비숍을 놓을 수 있는 모든 위치다
					else
						blacklist.add(new Node(i, j));
				} else {
					ary[i][j] = 1;// 놓을 수 없는 위치
				}

			}
		}
		whitesize = whitelist.size();
		blacksize = blacklist.size();
		whiteBishop(-1, 0);
		blackBishop(-1, 0);
		System.out.println(whiteMaxAnswer + blackMaxAnswer);
	}

	private static void whiteBishop(int beforeUsed, int cnt) {

		// 현재 카운트 + 나머지 카운트를 다 해도 정답보다 작으면 볼 필요가 없다
		if (cnt + whitesize - (beforeUsed + 1) <= whiteMaxAnswer)
			return;

		if (beforeUsed == whitesize - 1) { // 다 썼다
			whiteMaxAnswer = whiteMaxAnswer > cnt ? whiteMaxAnswer : cnt;
			return;
		}

		// 각 위치에 비숍을 놓는다
		Node node = whitelist.get(beforeUsed + 1);
		int i = node.i;
		int j = node.j;
		// 무조건 놓을 수 있는 위치 일수도 있다....현재 칸을 제외하고 영향을 전혀 안끼치면 무조건 놓을 수 있다
		boolean absolute = false;
		if (ary[i][j] == 0) {// 놓을 수 있다
			// 비숍의 경로를 모두 체크한다
			absolute = bishopAttackRange(i, j, 1);
			whiteBishop(beforeUsed + 1, cnt + 1);
			// 비숍의 경로를 체크 해제한다
			bishopAttackRange(i, j, -1);
		}

		if (!absolute)
			// 놓지 않는다, 혹은 놓을 수 없어서 놓지 않는다
			whiteBishop(beforeUsed + 1, cnt);

	}

	private static void blackBishop(int beforeUsed, int cnt) {

		// 현재 카운트 + 나머지 카운트를 다 해도 정답보다 작으면 볼 필요가 없다
		if (cnt + blacksize - (beforeUsed + 1) <= blackMaxAnswer)
			return;

		if (beforeUsed == blacksize - 1) { // 다 썼다
			blackMaxAnswer = blackMaxAnswer > cnt ? blackMaxAnswer : cnt;
			return;
		}

		// 각 위치에 비숍을 놓는다
		Node node = blacklist.get(beforeUsed + 1);
		int i = node.i;
		int j = node.j;
		// 무조건 놓을 수 있는 위치 일수도 있다....현재 칸을 제외하고 영향을 전혀 안끼치면 무조건 놓을 수 있다
		boolean absolute = false;
		if (ary[i][j] == 0) {// 놓을 수 있다
			// 비숍의 경로를 모두 체크한다
			absolute = bishopAttackRange(i, j, 1);
			blackBishop(beforeUsed + 1, cnt + 1);
			// 비숍의 경로를 체크 해제한다
			bishopAttackRange(i, j, -1);
		}

		if (!absolute)
			// 놓지 않는다, 혹은 놓을 수 없어서 놓지 않는다
			blackBishop(beforeUsed + 1, cnt);

	}

	private static boolean bishopAttackRange(int i, int j, int val) {
		boolean absolute = true;
		ary[i][j] += val;
		for (int idx = 0; idx < 2; idx++) {
			int dist = 1;
			while (true) {
				int di = dist * deli[idx] + i;
				int dj = dist * delj[idx] + j;
				if (di >= 0 && dj >= 0 && di < N && dj < N) {
					// 범위 내에 있다면
					if (ary[di][dj] == 0)
						absolute = false;
					ary[di][dj] += val;
					dist++;
				} else
					break;
			}
		}

		return absolute;
	}
}