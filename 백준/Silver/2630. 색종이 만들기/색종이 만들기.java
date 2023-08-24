import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int ary[][];
	static int oneCnt = 0;
	static int zeroCnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		ary = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 완료
		recursion(0, 0, n - 1, n - 1);
		System.out.println(zeroCnt + " " + oneCnt);
	}

	private static void recursion(int i1, int j1, int i2, int j2) {
		if (check(i1, j1, i2, j2)) {
			return;
		}
		int midi = (i1 + i2) / 2;
		int midj = (j1 + j2) / 2;
		recursion(i1, j1, midi, midj);
		recursion(midi + 1, j1, i2, midj);
		recursion(i1, midj + 1, midi, j2);
		recursion(midi + 1, midj + 1, i2, j2);
	}

	private static boolean check(int i1, int j1, int i2, int j2) {
		// 모두 같은 수 인지 확인. 1,0인지 에 따라 카운트 증가
		// 모두 같으면 트루
		//System.out.println(i1 + " " + j1 + " " + i2 + " " + j2);
		int one = 0;
		int zero = 0;
		for (int i = i1; i <= i2; i++) {
			for (int j = j1; j <= j2; j++) {
				if (ary[i][j] == 0)
					zero++;
				else
					one++;
			}
		}
		if (one == 0) {
			zeroCnt++;
			return true;
		}
		if (zero == 0) {
			oneCnt++;
			return true;
		}
		return false;
	}
}