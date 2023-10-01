import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int ary[] = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {

			ary[i] = Integer.parseInt(st.nextToken());

		}
		if (ary[0] == 0) {
			System.out.println(ary[1]);
		}
		if (ary[n - 1] == 0) {
			System.out.println(ary[n - 2]);
		}

		int l = 0;
		int r = n - 1;
		int answerA = Integer.MIN_VALUE;
		int answerB = Integer.MAX_VALUE;
		int answerSum = Integer.MAX_VALUE;
		while (r > l) {
//			System.out.println(l+" "+r);
			int a = ary[l];
			int b = ary[r];
			int sum = a + b;
			int absSum = Math.abs(sum);
			if (absSum <= answerSum) {
				answerA = ary[r];
				answerB = ary[l];
				answerSum = absSum;
			}
			if (sum < 0) {
				l++;
			}
			if (sum == 0)
				break;
			if (sum > 0) {
				r--;
			}
		}
		System.out.println(answerB+" "+answerA);
	}
}