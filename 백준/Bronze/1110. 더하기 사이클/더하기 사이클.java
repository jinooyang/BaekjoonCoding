
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class Main {

	public static void main(String[] args) {
		Scanner temp = new Scanner(System.in);

		int N = temp.nextInt();
		int n = N;
		
		for (int i = 1;; i++) {

			int sum = n / 10 + n % 10;
			//System.out.println("sum"  +sum);
			
			n = (n % 10)*10 + sum % 10;
			//System.out.println("newnum" + n);
			if (n == N) {
				System.out.println(i);
				break;
			}
		}
	}
}