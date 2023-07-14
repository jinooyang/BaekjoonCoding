import java.util.Scanner;
import java.io.FileInputStream;


class Solution
{
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
	
		for(int test_case = 1; test_case <= T; test_case++)
		{
			String answer = "Yes";
			
			
			int n;
			n=sc.nextInt();
			String s;
			s=sc.next();
			
			String front = s.substring(0,n/2);
			String back = s.substring(n/2,n);
			if(!front.equals(back)) {
				answer = "No";
			}
			
			System.out.println("#"+test_case+" "+answer);
			

		}
	}
}