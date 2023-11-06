import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int total = 0;
		int answer =0 ;
		for(int i=0;i<10;i++) {
			 st = new StringTokenizer(br.readLine());
			 int x = Integer.parseInt(st.nextToken());
			 int y = Integer.parseInt(st.nextToken());
			 total = total -x + y;
			 answer = Math.max(total, answer);
		}
		System.out.println(answer);
	}
}
