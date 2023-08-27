import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int ary[] = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}

		int start = 0;
		int end = 0;
		int sum = ary[0];
		int minAnswer = Integer.MAX_VALUE;
		while (start <= end) {
			if (sum < s) {
				end++;
				if (end >= n)
					break;
				sum += ary[end];
				continue;
			}

			if (sum >= s) {

				if (end - start + 1 < minAnswer) {
					// System.out.println(start + " -> " + end);
					minAnswer = end - start + 1;
				}

				sum -= ary[start];
				start++;
				continue;
			}

		}
		if (minAnswer == Integer.MAX_VALUE)
			System.out.println(0);

		else
			System.out.println(minAnswer);

	}
}
