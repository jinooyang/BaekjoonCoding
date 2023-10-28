import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < tc; t++) {
			int x = Integer.parseInt(br.readLine());
			int cnt = 0;
			int n = 0;
			while(cnt!=1023) {
				n++;
				int temp = x*n;
				
//				System.out.println(cnt);
				while(temp>0) {
					cnt = cnt | (1<<temp%10);
					temp = temp/10;
				}
				
			}
			
			int answer = n*x;
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);

	}
}