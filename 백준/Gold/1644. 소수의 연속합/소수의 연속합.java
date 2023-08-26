import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		if (n == 1) {
			System.out.println(0);
			return;
		}
		List<Integer> primeNumber = new ArrayList<>();
		for (int i = 2; i <= n; i++) {
			if (isPrimeNumber(i)) {
				primeNumber.add(i);
			}
		}
		int answer = 0;
		int start = 0;
		int end = 0;
		int sum = primeNumber.get(0);
//		for(int i=0;i<primeNumber.size();i++) {
//			System.out.print(primeNumber.get(i) + " ");
//		}
//		System.out.println();
		int size = primeNumber.size();
		while (end < size && start < size && start <= end) {
			// System.out.println("start : " + start+ ", end : " + end );
			if (sum == n) {
				// System.out.println("answerfound : start : " + start+ ", end : " + end );
				answer++;
			}
			if (start == end) {

				end++;
				if (end < size)
					sum += primeNumber.get(end);
				continue;
			}
			if (sum >= n) {
				if (start < size)
					sum -= primeNumber.get(start);
				start++;

				continue;
			}
			if (sum < n) {
				end++;
				if (end < size)
					sum += primeNumber.get(end);
				continue;
			}
			break;
		}
		System.out.println(answer);

	}

	private static boolean isPrimeNumber(int x) {
		if (x == 2)
			return true;
		int s = (int) Math.sqrt(x);
		for (int i = 2; i <= s; i++) {
			if (x % i == 0)
				return false;
		}

		return true;
	}
}