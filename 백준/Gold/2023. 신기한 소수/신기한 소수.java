import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {

	static boolean isPrime(int a) {
		if (a < 2)
			return false;
		int num = (int) Math.sqrt(a);
		for (int k = 2; k <= num; k++) {
			if (a % k == 0) {
				// System.out.println(a + "is not prime");
				return false;
			}
		}
		return true;
	}

	static int n;
	static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		// 입력
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		funnyPrime(0, 0);
		bw.flush();
		bw.close();

	}

	private static void funnyPrime(int beforeNum, int beforeLength) throws IOException {
		for (int i = 0; i < 10; i++) {
			int temp = beforeNum * 10 + i;
			if (isPrime(temp)) {
				// System.out.println(temp);
				if (beforeLength < n - 1)
					funnyPrime(temp, beforeLength + 1);
				else
					bw.write(temp + "\n");
			}
		}
	}
}