import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		int c = Integer.parseInt(br.readLine());
		int mul = a * b * c;
		int ary[] = new int [10];
		while(mul>0) {
			int r = mul%10;
			ary[r]++;
			mul = mul/10;
		}
		
		for(int i=0;i<10;i++) {
			System.out.println(ary[i]);
		}
		
	}
}