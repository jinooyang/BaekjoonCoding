import java.util.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n, b, c;
		List<Long> a = new ArrayList<>();
		n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			a.add(sc.nextLong());
		}
		b = sc.nextInt();
		c = sc.nextInt();

		long answer = a.size();

		for (int i = 0; i < a.size(); i++) {
			long students = a.get(i);
			students -= b;
			if (students > 0) {
				long num = students / c;
				if (students % c != 0) {
					num += 1;
				}
				answer += num;
			}
		}
		System.out.println(answer);
	}

}