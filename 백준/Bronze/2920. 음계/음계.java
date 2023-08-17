import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int ary[] = new int[8];
		for (int i = 0; i < 8; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}
		boolean isSort = true;
		for (int i = 1; i < 7; i++) {
			if (ary[i - 1] - ary[i] != ary[i] - ary[i + 1])
				isSort = false;
		}
		if (isSort) {
			if (ary[0] == 1)
				System.out.println("ascending");
			else
				System.out.println("descending");
		} else
			System.out.println("mixed");
	}
}