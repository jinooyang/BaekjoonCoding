import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int a[] = new int[n];
		int b[] = new int[n];
		int c[] = new int[n];
		int d[] = new int[n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			a[i] = Integer.parseInt(st.nextToken());
			b[i] = Integer.parseInt(st.nextToken());
			c[i] = Integer.parseInt(st.nextToken());
			d[i] = Integer.parseInt(st.nextToken());
		}
		int ab[] = new int[n * n];
		int cd[] = new int[n * n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ab[i * n + j] = a[i] + b[j];
				cd[i * n + j] = c[i] + d[j];
			}
		}
//		Collections.sort(ab);
		Arrays.sort(cd);
		long answer = 0;
		for (int i = 0; i < ab.length; i++) {
			int x = -1 * ab[i];

			int l = lowerBound(cd, x);
			int u = upperBound(cd, x);
			answer += u - l;

		}
		System.out.println(answer);
	}

	private static int lowerBound(int[] data, int target) {
		int begin = 0;
		int end = data.length;

		while (begin < end) {
			int mid = (begin + end) / 2;

			if (data[mid] >= target) {
				end = mid;
			} else {
				begin = mid + 1;
			}
		}
		return end;
	}

	private static int upperBound(int[] data, int target) {
		int begin = 0;
		int end = data.length;

		while (begin < end) {
			int mid = (begin + end) / 2;

			if (data[mid] <= target) {
				begin = mid + 1;
			} else {
				end = mid;
			}
		}
		return end;
	}
}