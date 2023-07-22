import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		// 오르막 수의 개수?

		int ary[][] = new int[11][n + 1];
		for (int i = 0; i < 10; i++) {
			ary[i][1] = 1;
		}

		ary[10][1] = 10;

		for (int j = 2; j <= n; j++) {

			ary[0][j] = ary[10][j - 1];
			int sum = ary[0][j];
			for (int i = 1; i < 10; i++) {
				ary[i][j] = (ary[i - 1][j] % 10007 - ary[i - 1][j - 1] % 10007) % 10007;
				if (ary[i][j] < 0)
					ary[i][j] += 10007;
				sum += ary[i][j];
			}
			ary[10][j] = sum % 10007;
		}
		System.out.println(ary[10][n]);
	}
}