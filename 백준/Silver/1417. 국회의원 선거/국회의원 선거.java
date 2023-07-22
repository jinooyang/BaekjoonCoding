import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// 제일 많은 곳에서 한명씩 델고 간다
		Scanner sc = new Scanner(System.in);

		// 후보 수
		int n = sc.nextInt();
		//System.out.println("n : " + n);
		// 다솜이
		int dasom = sc.nextInt();
		
		if (n == 1) {
			System.out.println(0);

		} else {
			// 나머지
			int other[] = new int[n - 1];
			for (int i = 0; i < n - 1; i++) {
				other[i] = sc.nextInt();
			}
			Arrays.sort(other);
			// 제일 많은 곳에서 한명씩 델고 간다
			int answer = 0;
			while (n >= 2) {

				if (dasom > other[n - 2])// 다솜이 수가 가장 크면 종료
					break;
				other[n - 2] -= 1;
				dasom += 1;
				answer += 1;
				Arrays.sort(other);
//				for(int num : other) {
//					System.out.print(num + " ");
//				}
//				System.out.println();
			}
			System.out.print(answer);

		}
	}
}