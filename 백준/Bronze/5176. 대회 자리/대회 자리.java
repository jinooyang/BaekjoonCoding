import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int n =  Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			boolean ary[] = new boolean[m+1];
			int ans = 0;
			for(int i=0;i<n;i++) {
				int x = Integer.parseInt(br.readLine());
				if(!ary[x]) {
					ary[x] = true;
					continue;
				}
				else {
					ans++;
				}
			}
			System.out.println(ans);
		}
	}
}