import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long n = Integer.parseInt(st.nextToken());
		long dice[] = new long[6];
		st = new StringTokenizer(br.readLine());
		long oneMin = Integer.MAX_VALUE;
		long twoMin = Integer.MAX_VALUE;
		long threeMin = Integer.MAX_VALUE;
		int allSum = 0;
		long allMax = 0;
		for (int i = 0; i < 6; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
			allSum += dice[i];
			if (dice[i] > allMax)
				allMax = dice[i];
			if (dice[i] < oneMin)
				oneMin = dice[i];
		}
		if (n == 1) {
			System.out.println(allSum - allMax);
			return;
		}
		for (int i = 0; i < 5; i++) {
			for (int j = i + 1; j < 6; j++) {
				if (i + j == 5)
					continue;
				long sum = dice[i] + dice[j];
				if (twoMin > sum)
					twoMin = sum;
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = i + 1; j < 5; j++) {
				for (int k = j + 1; k < 6; k++) {
					if (i + j == 5)
						continue;
					if (j + k == 5)
						continue;
					if (k + i == 5)
						continue;

					long sum = dice[i] + dice[j] + dice[k];
					if (threeMin > sum)
						threeMin = sum;
				}
			}
		}
//		System.out.println(oneMin + " " + twoMin + " " + threeMin);
		long answer = 0;
		long w = Math.max(n - 2, 0);
		long oneSum = w * w * 5 + w * 4;
		oneSum *= oneMin;

		long twoSum = 8 * n - 12;
		twoSum = Math.max(twoSum, 0);
		twoSum *= twoMin;

		long threeSum = 4;
		threeSum *= threeMin;

		answer += oneSum + twoSum + threeSum;
		System.out.println(answer);
	}
}