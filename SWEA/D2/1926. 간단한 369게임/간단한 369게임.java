import java.util.*;


public class Solution {
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		Integer n = sc.nextInt();
		for(Integer i=1;i<=n;i++) {
			String num = i.toString();
			int cnt = 0;
			for(int j=0;j<num.length();j++) {
				if(num.charAt(j)=='3' || num.charAt(j)=='6' || num.charAt(j)=='9' ) {
					cnt++;
				}
			}
			//System.out.println( i  +" :  " + cnt);
			if(cnt == 0) {
				System.out.print(i);
			}
			
			else {
				for(int c=0;c<cnt;c++) {
					System.out.print("-");
				}
				
			}
			
			
			
			System.out.print(" ");
		}	
	}
}