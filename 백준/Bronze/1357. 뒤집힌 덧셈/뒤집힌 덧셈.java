import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		sb.append(st.nextToken());
		sb2.append(st.nextToken());
		
		int a = Integer.parseInt(sb.reverse().toString());
		int b = Integer.parseInt(sb2.reverse().toString());
		Integer x = a+b;
		StringBuilder ans = new StringBuilder();
		ans.append(x.toString());
		System.out.println(Integer.parseInt(ans.reverse().toString()));
		
	}
}