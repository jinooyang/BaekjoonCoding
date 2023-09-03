import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int total = 0;
		int ary[] = new int[5];
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			total += n;
			ary[i] = n;

		}
		Arrays.sort(ary);
		System.out.println(total/5);
		System.out.println(ary[2]);

	}
}