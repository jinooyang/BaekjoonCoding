import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int N = scan.nextInt();
		long[] arr = new long[N+1];
		arr[1] = 5;
		for(int i=2; i<arr.length; i++) {
			arr[i] = arr[i-1] + (i*5) + (1 - (i*2));
		}
		
		System.out.println(arr[N] % 45678);
		scan.close();
	}

}