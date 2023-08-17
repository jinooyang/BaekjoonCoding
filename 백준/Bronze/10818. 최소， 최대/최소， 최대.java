import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int ary[] = new int[n];
		for (int i = 0; i < n; i++) {
			ary[i] = Integer.parseInt(st.nextToken());

		}
		Arrays.sort(ary);
		System.out.println(ary[0] + " " + ary[n-1]);
	}
}