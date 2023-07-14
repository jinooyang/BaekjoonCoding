import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int hour = sc.nextInt();
		int min = sc.nextInt();
		
		int time = sc.nextInt();
		
		min  = min + time;
		while(min >=60) {
			hour ++;
			if(hour == 24) hour  = 0;
			min = min-60;
		}
		
		System.out.println(hour +" "+ min);
	}
}