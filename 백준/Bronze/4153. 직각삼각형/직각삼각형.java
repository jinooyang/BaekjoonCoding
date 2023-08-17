import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int ary[] = new int[3];
			StringTokenizer st = new StringTokenizer(br.readLine());
			ary[0] = Integer.parseInt(st.nextToken());
			ary[1] = Integer.parseInt(st.nextToken());
			ary[2] = Integer.parseInt(st.nextToken());
			if (ary[0] == 0 && ary[1] == 0 && ary[2] == 0)
				break;
			Arrays.sort(ary);
			if (Math.pow(ary[0], 2) + Math.pow(ary[1], 2) == Math.pow(ary[2], 2)) {
				System.out.println("right");
			} else
				System.out.println("wrong");

		}
	}
}