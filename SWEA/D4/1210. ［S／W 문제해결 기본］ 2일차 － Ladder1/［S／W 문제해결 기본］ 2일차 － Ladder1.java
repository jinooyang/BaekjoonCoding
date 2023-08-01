import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static boolean found = false;
	static int ary[][] = new int[100][100];
	static int tc = 0;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int test_case = 1; test_case <= 10; test_case++) {
			found = false;
			StringTokenizer st = new StringTokenizer(br.readLine());// 필요 없는 테케 번호는 날리고
			tc = Integer.parseInt(st.nextToken());
			// 입력
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					ary[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int j = 0; j < 100; j++) {
				if (ary[0][j] == 1) {
					//System.out.println("start : " + j);
					ladder(j);// 시작점 찾아서 사다리 태운다
				}
				if (found)
					break;
			}
		}
	}

	private static void ladder(int start) {
		int i = 0;
		int j = start;
		while (i < 99) {
			boolean moveleft = false;
			while (j - 1 >= 0 && ary[i][j - 1] != 0) {
				moveleft = true;
				j = j - 1;
			}
			while (j + 1 < 100 && ary[i][j + 1] != 0 && !moveleft)
				j = j + 1;
			i++;
			//System.out.print("(" + i + ", " + j + ") => ");
		}
	//	System.out.println();
	//	System.out.println(i + " " + j);
		if (ary[i][j] == 2) {
			System.out.println("#" + tc + " " + start);
			found = true;
		}
	}
}