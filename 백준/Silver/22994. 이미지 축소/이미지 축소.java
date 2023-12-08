import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int m;

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		char ary[][] = new char[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < m; j++) {
				char x = s.charAt(j);
				ary[i][j] = x;
			}
		}
//---------------------------------------------------------------------------------------------------------------------------
		int acrossMax = 0;
		List<Integer> across = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			char before = ' ';
			int cnt = 0;
			int max = 0;
			List<Integer> list = new ArrayList<>();
			for (int j = 0; j < m; j++) {
				char now = ary[i][j];
				if (before != ' ' && before != now) {
					list.add(cnt);
					max = Math.max(max, cnt);
					cnt = 0;
				}
				before = now;
				cnt++;
			}
			list.add(cnt);
			max = Math.max(max, cnt);
			// 각 행에서 GCD를 찾는다
			int gcd = getGCD(list, max);
			across.add(gcd);
			acrossMax = Math.max(gcd, acrossMax);
		}

		int acrossGCD = getGCD(across, acrossMax);

		// System.out.println(acrossGCD);
//-------------------------------------------------------------------------------------------------------------------------
		int downMax = 0;
		List<Integer> down = new ArrayList<>();
		for (int j = 0; j < m; j++) {
			char before = ' ';
			int cnt = 0;
			int max = 0;
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				char now = ary[i][j];
				if (before != ' ' && before != now) {
					list.add(cnt);
					max = Math.max(max, cnt);
					cnt = 0;
				}
				before = now;
				cnt++;
			}
			list.add(cnt);
			max = Math.max(max, cnt);
			// 각 행에서 GCD를 찾는다
			int gcd = getGCD(list, max);
			down.add(gcd);
			downMax = Math.max(gcd, downMax);
		}

		int downGCD = getGCD(down, downMax);
		// System.out.println(downGCD);
		System.out.println(n / downGCD + " " + m / acrossGCD);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i += downGCD) {

			for (int j = 0; j < m; j += acrossGCD) {
				sb.append(ary[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int getGCD(List<Integer> list, int max) {
		if (list.size() == 1)
			return list.get(0);
		int res = 1;
		for (int i = 1; i <= max; i++) {
			boolean possible = true;
			for (int idx = 0; idx < list.size(); idx++) {
				if (list.get(idx) % i != 0)
					possible = false;
			}
			if (possible)
				res = Math.max(res, i);
		}

		return res;
	}
}