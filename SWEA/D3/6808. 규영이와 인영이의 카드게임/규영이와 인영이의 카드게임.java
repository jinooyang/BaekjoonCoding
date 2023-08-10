import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static int in[];
	static int kyu[];
	static int kyuWin;
	static int kyuLose;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		// int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			kyuWin = 0;
			kyuLose = 0;
			kyu = new int[9];
			in = new int[9];
			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < 9; i++) {
				kyu[i] = Integer.parseInt(st.nextToken());
			}

			int inSize = 0;
			for (int i = 1; i <= 18; i++) {
				boolean found = false;
				for (int k = 0; k < 9; k++) {
					if (kyu[k] == i) {
						found = true;
						break;
					}
				}
				if (!found)
					in[inSize++] = i;
			}

//			for(int i=0;i<9;i++) {
//				System.out.print(in[i] + " ");
//			}
//			System.out.println();

			comb(0, 0, new int[9]);
			System.out.println("#" + test_case + " " + kyuWin + " " + kyuLose);
		}
	}

	// 사용된 인덱스
	private static void comb(int cnt, int beforeNum, int comblist[]) {// comblist에 인영이 카드의 인덱스를 넣자
		if (cnt == 9) {
			int kyuScore = 0;
			int inScore = 0;
			for (int i = 0; i < 9; i++) {
				int x = comblist[i];
				if (kyu[i] > in[x]) {
					kyuScore += kyu[i] + in[x];
				} else
					inScore += kyu[i] + in[x];
			}
			if (kyuScore > inScore)
				kyuWin++;
			else if (inScore > kyuScore)
				kyuLose++;
			return;
		}
		for (int i = 0; i < 9; i++) {
			if (((1 << i) & beforeNum) == 0) {
				comblist[cnt] = i;
				comb(cnt + 1, (1 << i) | beforeNum, comblist);
			}
		}

	}
}