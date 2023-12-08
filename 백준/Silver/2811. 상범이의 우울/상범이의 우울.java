import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int ary[] = new int[n];
		boolean flowers[] = new boolean[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}
		int T = 0;
		int start = 0;
		List<Integer> maxTList = new ArrayList<>();
		int maxT = 0;
		for (int i = 0; i < n; i++) {
			if (ary[i] < 0) {
				if (T == 0)
					start = i;
				T++;
				if (T > maxT) {
					maxT = T;
					maxTList = new ArrayList<>();
				}
				if (maxT == T) {
					maxTList.add(start);
				}

				if (i + 1 == n) {
					// 꽃을 사준다
					buyFlowers(start, T, flowers);
				} else if (ary[i + 1] < 0) {
					// 꽃을 나중에 사준다
					continue;
				} else if (ary[i + 1] >= 0) {
					// 꽃을 사준다
					buyFlowers(start, T, flowers);
				}
			} else
				T = 0;
		}

		int maxTFlowers = addMaxT(maxTList, maxT, flowers);
//		System.out.println("add : " + maxTFlowers);
		int ans = maxTFlowers;
		for (int i = 0; i < n; i++) {
			if (flowers[i])
				ans++;
		}
		System.out.println(ans);
	}

	private static int addMaxT(List<Integer> maxTList, int maxT, boolean[] flowers) {
		int maxCnt = 0;
//		System.out.println(maxT);
		for (int i = 0; i < maxTList.size(); i++) {
			int start = maxTList.get(i);
			int cnt = 0;
			for (int j = 1; j <= 3 * maxT; j++) {
				if (start - j < 0)
					break;
				if (!flowers[start - j])
					cnt++;
			}
			maxCnt = Math.max(cnt, maxCnt);

		}
		return maxCnt;
	}

	private static void buyFlowers(int start, int t, boolean[] flowers) {
		for (int i = 1; i <= 2 * t; i++) {
			if (start - i < 0)
				break;
			flowers[start - i] = true;
		}
	}
}