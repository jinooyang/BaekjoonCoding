import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {

		// 알고리즘 요약
		// 접는 순서에 따라 2*2배열을 상하반전 혹은 좌우 반전 시킨다.
		// 뚫는 구멍에 따라 2*2배열을 수정한다
		// 이 배열을 반복적으로 출력한다

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력준비
		StringTokenizer st = new StringTokenizer(br.readLine()); // 입력준비
		int k = Integer.parseInt(st.nextToken());// 접는 횟수
		boolean isUp = true;// 마지막으로 접은게 상인지 하인지 판단
		boolean isLeft = true;// 마지막으로 접은게 좌인지 우인지 판단
		int ary[][] = { { 0, 1 }, { 2, 3 } };//
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * k; i++) {// 종이 접는 방법을 나타내는 문자가 2k개
			char fold = st.nextToken().charAt(0);
			if (fold == 'R') {// 오른 쪽으로 접은 경우
				if (isLeft) {// 이전에 왼쪽으로 접힌 경우 좌우 반전한다
					swapLeftRight(ary);// 좌우 반전
					isLeft = !isLeft;// 접은 방향 기록
				}
			} else if (fold == 'L') {// 왼쪽으로 접은 경우
				if (!isLeft) {// 이전에 왼쪽으로 접힌 경우가 아니면 좌우 반전한다
					swapLeftRight(ary);// 좌우 반전
					isLeft = !isLeft;// 접은 방향 기록
				}
			} else if (fold == 'D') {// 아래로 접은경우
				if (isUp) {// 이전에 위쪽으로 접힌 경우가 상하 반전한다
					swapUpDown(ary);// 상하 반전
					isUp = !isUp;// 접은 방향 기록
				}
			} else {// 이전에 위쪽으로 접힌 경우가 아니면 상하 반전한다
				if (!isUp) {
					swapUpDown(ary);// 상하 반전
					isUp = !isUp;// 접은 방향 기록
				}
			}
		}
		st = new StringTokenizer(br.readLine());// 출력 준비
		int hole = Integer.parseInt(st.nextToken());// 어디에 구멍을 뚫을지 판단
		if (hole == 1) {// 1 이면 좌우 반전
			swapLeftRight(ary);// 좌우 반전
		} else if (hole == 2) {// 2 이면 상하 반전
			swapUpDown(ary);// 상하 반전
		} else if (hole == 3) {// 3 이면 상하좌우반전
			swapUpDown(ary);// 상하 반전
			swapLeftRight(ary);// 좌우 반전
		}

		// ary를 출력한다 k * k번 출력한다
		StringBuilder sb = new StringBuilder();// 스티링 빌더로 결과 출력
		for (int i = 0; i < (1 << k); i++) {// 반복문
			for (int j = 0; j < (1 << k); j++) {// 반복문
				sb.append(ary[i % 2][j % 2]).append(" ");// 스트링 빌더 append
			}
			sb.append("\n");// 스트링 빌더 줄바꿈
		}
		System.out.println(sb.toString());// 결과 출력
	}

	private static void swapUpDown(int[][] ary) {// 상하 반전
		for (int j = 0; j < 2; j++) {// temp에 저장하여 상하 반전
			int temp = ary[0][j];// temp에 저장하여 상하 반전
			ary[0][j] = ary[1][j];// temp에 저장하여 상하 반전
			ary[1][j] = temp;// temp에 저장하여 상하 반전
		}
	}

	private static void swapLeftRight(int[][] ary) {// 좌우 반전
		for (int i = 0; i < 2; i++) {// temp 에 저장하여 좌우반전
			int temp = ary[i][0];// temp 에 저장하여 좌우반전
			ary[i][0] = ary[i][1];// temp 에 저장하여 좌우반전
			ary[i][1] = temp;// temp 에 저장하여 좌우반전
		}
	}
}