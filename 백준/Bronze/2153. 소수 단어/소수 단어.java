import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String word = sc.next();
		int sum = 0;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if ('A' <= c && c <= 'Z') {
				int tempNum = c - 'A' + 27;
				sum += tempNum;
			}
			if ('a' <= c && c <= 'z') {
				int tempNum = c - 'a' + 1;
				sum += tempNum;
			}
		}
		//System.out.println("sum : " + sum);
		int isPrime = 0;
		for (int i = 1; i <= sum; i++) {
			if (sum % i == 0)
				isPrime++;
		}

		if (isPrime <= 2) {
			System.out.println("It is a prime word.");
		} else {
			System.out.println("It is not a prime word.");
		}

	}
}