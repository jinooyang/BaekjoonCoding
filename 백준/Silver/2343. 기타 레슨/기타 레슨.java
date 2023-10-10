import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int ary[];
	static int m;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		ary = new int[n];
		int sum = 0;
		for (int i = 0; i < n; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
			sum += ary[i];
		}
		int s = sum / m;
		int e = sum;
		int answer = Integer.MAX_VALUE;
		while (s <= e) {
			int mid = (s + e) >> 1;
			boolean poss = check(mid);
			if (poss) {
				answer = Math.min(answer, mid);
				e = mid - 1;
			} else {
				s = mid + 1;
			}

		}
		System.out.println(answer);
	}

	private static boolean check(int mid) {
		int sum = 0;
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			if (ary[i] > mid)
				return false;
			if (sum + ary[i] <= mid) {
				sum += ary[i];
			} else {
				cnt++;
				sum = 0;
				i--;
			}
		}
		if (sum > 0)
			cnt++;
		if (cnt <= m)
			return true;
		else
			return false;

	}
}