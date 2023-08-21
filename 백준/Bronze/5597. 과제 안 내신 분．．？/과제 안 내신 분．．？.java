import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		boolean ary[] = new boolean[31];
		for (int i = 0; i < 28; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			ary[n] = true;
		}
		for (int i = 1; i < 31; i++) {
			if (ary[i] == false)
				System.out.println(i);
		}
	}
}