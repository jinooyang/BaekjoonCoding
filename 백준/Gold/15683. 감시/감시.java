import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
	}

	static List<Node> list;
	static int N;
	static int M;
	static int ary[][];
	static int cctvCnt;
	static int minAnswer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());// 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		minAnswer = Integer.MAX_VALUE;
		ary = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
				if (ary[i][j] >= 1 && ary[i][j] <= 5)
					list.add(new Node(i, j));
			}
		}
		cctvCnt = list.size();

		// 순열로 씨씨티비 방향 생성
		int directionAry[] = new int[cctvCnt];
		getCombo(0, directionAry);
		// 순열의 끝에선 ary생성해서 체크로 넘김
		System.out.println(minAnswer);
	}

	private static void getCombo(int cnt, int directionAry[]) {
		if (cnt == cctvCnt) {
			int tempAry[][] = new int[N][M];// 임시 배열 생성
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					tempAry[i][j] = ary[i][j];
				}
			}
			for (int i = 0; i < cctvCnt; i++) {// tempAry 수정
				int direction = directionAry[i];
				Node node = list.get(i);
				cctv(node.i, node.j, direction, tempAry);
			}
			int currentBlindSpot = countBlindSpot(tempAry);
			if (currentBlindSpot < minAnswer)
				minAnswer = currentBlindSpot;
			return;
		}
		for (int i = 0; i < 4; i++) {// 방향을 정해준다
			directionAry[cnt] = i;// 현재 씨씨티비의 방향을 설정해준다
			getCombo(cnt + 1, directionAry);
		}
	}

	private static void cctv(int i, int j, int direction, int[][] tempAry) {
		int cctvNum = ary[i][j];
		{
			switch (cctvNum) {
			case 1:
				lookForward(i, j, (0 + direction) % 4, tempAry);
				break;
			case 2:
				lookForward(i, j, (0 + direction) % 4, tempAry);
				lookForward(i, j, (2 + direction) % 4, tempAry);
				break;
			case 3:
				lookForward(i, j, (0 + direction) % 4, tempAry);
				lookForward(i, j, (1 + direction) % 4, tempAry);
				break;
			case 4:
				lookForward(i, j, (0 + direction) % 4, tempAry);
				lookForward(i, j, (1 + direction) % 4, tempAry);
				lookForward(i, j, (3 + direction) % 4, tempAry);
				break;
			case 5:
				lookForward(i, j, (0 + direction) % 4, tempAry);
				lookForward(i, j, (1 + direction) % 4, tempAry);
				lookForward(i, j, (2 + direction) % 4, tempAry);
				lookForward(i, j, (3 + direction) % 4, tempAry);
				break;
			default:
				break;
			}
		}
	}

	static int deli[] = { -1, 0, 1, 0 };// 상우하좌
	static int delj[] = { 0, 1, 0, -1 };

	private static void lookForward(int i, int j, int direction, int[][] tempAry) {// 좌표와 방향이 들어왔을 때 현재 방향으로 쭉 보고 -1로
																					// 체크한다
		int dist = 1;
		while (true) {
			int di = i + deli[direction] * dist;
			int dj = j + delj[direction] * dist;
			if (di >= 0 && dj >= 0 && di < N && dj < M && tempAry[di][dj] != 6) {// 범위 내에 있다, 벽이 아니다
				tempAry[di][dj] = -1;// 봤다는 것을 -1로 표기한다
				dist++;
			} else// 범위 밖이거나 벽이면 브레이크
				break;
		}
	}

	private static int countBlindSpot(int[][] tempAry) { // 사각지대를 카운트한다
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tempAry[i][j] == 0)
					result++;
			}
		}
		return result;
	}
}