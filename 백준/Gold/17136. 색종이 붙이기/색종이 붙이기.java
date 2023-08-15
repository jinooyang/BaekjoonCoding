import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int ary[][] = new int[10][10];
		int ones = 0;
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
				if (ary[i][j] == 1)
					ones++;
			}
		}
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 1; i <= 5; i++) {
			map.put(i, 5);
		}

		recursion(0, 0, ary, 5, map, 0, ones);
		if (answer == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(answer);
	}

	private static void recursion(int starti, int startj, int[][] ary, int paperSize, Map<Integer, Integer> map,
			int paperUsed, int onesLeft) {
		//if (paperSize == 5) {
			//System.out.println("starti : " + starti + ", " + "startj : " + startj + ", " + "paperSize : " + paperSize
			//		+ ", " + "paperUsed : " + paperUsed + ", " + "onesLeft : " + onesLeft);
		//}
		if (onesLeft == 0) {// 1짜리 종이까지 다 확인하고 옴, 보드를 다 채움
			if (paperUsed < answer)
				answer = paperUsed;
			return;
		}

		int remain = 0;
		for (Integer key : map.keySet()) {
			if (key <= paperSize)
				remain += map.get(key) * key * key;
		}
		if (remain < onesLeft) {

			return;
		}

		if (paperSize > 1)
			recursion(0, 0, ary, paperSize - 1, map, paperUsed, onesLeft);// 현재 종이 크기 더이상 쓰지 말자

		if (map.get(paperSize) > 0) {// 쓸 종이가 남은 경우
			for (int i = 0; i < 10; i++) {
				if (i < starti)
					continue;
				for (int j = 0; j < 10; j++) {
					if (i == starti && j < startj)
						continue;
					if (ary[i][j] == 1) {
						if (ableToAddPaperToBoard(i, j, paperSize, ary)) {

							addPaperToBoard(i, j, paperSize, ary);// 1->0
							map.put(paperSize, map.get(paperSize) - 1);
							recursion(i, j, ary, paperSize, map, paperUsed + 1, onesLeft - (paperSize * paperSize));
							map.put(paperSize, map.get(paperSize) + 1);
							removePaperFromBoard(i, j, paperSize, ary);// 0->1
						}
					}
				}
			}
		}

	}

	private static void removePaperFromBoard(int starti, int startj, int paperSize, int[][] ary) {
		for (int i = starti; i < starti + paperSize; i++) {
			for (int j = startj; j < startj + paperSize; j++) {
				ary[i][j] = 1;
			}
		}
	}

	private static void addPaperToBoard(int starti, int startj, int paperSize, int[][] ary) {
		for (int i = starti; i < starti + paperSize; i++) {
			for (int j = startj; j < startj + paperSize; j++) {
				ary[i][j] = 0;
			}
		}
	}

	private static boolean ableToAddPaperToBoard(int starti, int startj, int paperSize, int[][] ary) {
		if (starti + paperSize - 1 >= 10)
			return false;
		if (startj + paperSize - 1 >= 10)
			return false;
		boolean answer = true;
		for (int i = starti; i < starti + paperSize; i++) {
			for (int j = startj; j < startj + paperSize; j++) {
				if (ary[i][j] != 1) {
					answer = false;
					break;
				}
			}
			if (!answer)
				break;
		}
		return answer;
	}

}