import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int ary[] = new int[3];
		ary[0] = Integer.parseInt(st.nextToken());
		ary[1] = Integer.parseInt(st.nextToken());
		ary[2] = Integer.parseInt(st.nextToken());

		Arrays.sort(ary);

		int k = ary[0] + ary[1];

		while (ary[2] >= k) {
			ary[2]--;
		}
		System.out.println(ary[0] + ary[1] + ary[2]);

	}
}
