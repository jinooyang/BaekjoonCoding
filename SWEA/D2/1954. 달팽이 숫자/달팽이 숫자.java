import java.util.Scanner;

class Solution {
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			//System.out.println("tc : " + test_case);
			int n = sc.nextInt();
			//System.out.println(n);
			int ary[][] = new int[n][n];
			ary[0][0] = 1;
			int i = 0;
			int j = 0;
			int direction = 0;
			int deli[] = { 0, 1, 0, -1 };
			int delj[] = { 1, 0, -1, 0 };
			//System.out.println("here");
			for (int x = 2; x <= n * n; x++) {
			//	System.out.println("here");
				int di = i + deli[direction % 4];
				int dj = j + delj[direction % 4];
				if (di >= 0 && dj >= 0 && di < n && dj < n && ary[di][dj] == 0) {
					ary[di][dj] = x;
					i = di;
					j = dj;
				} else {
					direction++;
					x--;
				}

			}
			System.out.println("#" + test_case + " ");
			for (int y = 0; y < n; y++) {
				for (int x = 0; x < n; x++) {
					System.out.print(ary[y][x] + " ");
				}
				System.out.println();
			}
		}
	}
}