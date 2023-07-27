import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int n = 0;
	public static int m = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		int ary[][] = new int[n + 2][n + 2];
		int sumAry[][] = new int[n + 2][n + 2];
		int answer = 0;
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= i; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
			}
			for (int j = 1; j <= i; j++) {
//				System.out.println(i + "줄 : " + Math.max(ary[i - 1][j - 1], ary[i - 1][j]) + " + " +  ary[i][j]);
				sumAry[i][j] = Math.max(sumAry[i - 1][j - 1], sumAry[i - 1][j]) + ary[i][j];
				if (sumAry[i][j] > answer)
					answer = sumAry[i][j];
			}
		}
//		for(int i=1;i<=n;i++) {
//			for(int j=1;j<=n;j++) {
//				System.out.print(ary[i][j]);
//			}
//			System.out.println();
//		}
//		
//		System.out.println();
//		for(int i=1;i<=n;i++) {
//			for(int j=1;j<=n;j++) {
//				System.out.print(sumAry[i][j] +  " ");
//			}
//			System.out.println();
//		}
		System.out.println(answer);
	}
}