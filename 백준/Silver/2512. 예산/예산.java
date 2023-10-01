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
		st = new StringTokenizer(br.readLine());

		int m = Integer.parseInt(st.nextToken());

		Arrays.sort(ary);

		int s = 0;
		int e = ary[n - 1];
		int maxSum = 0;
		int maxAnswer = 0;
		while (s <= e) {
			int mid = (s + e) >> 1;
			int sum = 0;
			for (int i = 0; i < n; i++) {
				if (ary[i] < mid)
					sum += ary[i];
				else
					sum += mid;
			}

			if (sum <= m) {
				// 오른쪽에서 찾는다
				if (sum > maxSum) {
					maxSum = sum;
					maxAnswer = mid;
				}
//				if(sum==maxSum) {
//					maxAnswer = Math.max(mid, maxAnswer);
//				}
				s = mid + 1;
			}
//			if (sum == m) {
//				if (sum > maxSum) {
//					maxSum = sum;
//					maxAnswer = mid;
//				}
//				break;
//			}
			if (sum > m) {
				e = mid - 1;
			}
		}
		System.out.println(maxAnswer);

	}
}