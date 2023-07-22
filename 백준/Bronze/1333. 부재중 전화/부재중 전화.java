import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n, l, d;
		n = sc.nextInt();
		l = sc.nextInt();
		d = sc.nextInt();

		int time = 0;
		int answer = -1;
		for (int i = 0; i < n; i++) {
			time += l;

			for (int x = time; x < time + 5; x++) {
				if (x % d == 0) {
					answer = x;
					break;
				}
			}
			if (answer != -1)
				break;
			time = time + 5;
		}
		if (answer == -1) {
			answer =l*n + 5*(n-1);
		}
		
		while(true) {
			if(answer%d==0) {
				break;
			}
			else answer++;
		}
		System.out.println(answer);
	}

}