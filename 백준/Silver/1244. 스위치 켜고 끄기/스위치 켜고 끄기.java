import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int switchNum;
	static boolean ary[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		switchNum = Integer.parseInt(st.nextToken());
		ary = new boolean[switchNum + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= switchNum; i++) {
			int temp = Integer.parseInt(st.nextToken());
			if (temp == 1) {
				ary[i] = true;
			}
		}
		st = new StringTokenizer(br.readLine());
		int student = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= student; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());

			if (gender == 1) {

				boyswtich(num);
			} else {
				girlswitch(num);
			}
		}

		for (int i = 1; i <= switchNum; i++) {
			if (ary[i])
				System.out.print("1 ");
			else
				System.out.print("0 ");
			if (i % 20 == 0)
				System.out.println();
		}
	}

	private static void girlswitch(int num) {
		// 여학생은 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로
		// 좌우가 대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아서,
		// 그 구간에 속한 스위치의 상태를 모두 바꾼다.
		int start = num;
		int end = num;

		while (start - 1 >= 1 && end + 1 <= switchNum) {

			if (ary[start - 1] == ary[end + 1]) {// 좌우가 같다면
				start--;
				end++;
			} else
				break;
		}

		for (int i = start; i <= end; i++) {
			ary[i] = !ary[i];
		}

	}

	private static void boyswtich(int num) {
		// 남학생은 스위치 번호가 자기가 받은 수의 배수이면,
		// 그 스위치의 상태를 바꾼다.
		int i = 1;
		while (num * i <= switchNum) {
			ary[num * i] = !ary[num * i];
			i++;
		}

	}
}