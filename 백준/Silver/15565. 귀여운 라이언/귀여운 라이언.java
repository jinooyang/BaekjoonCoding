import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		List<Integer> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int temp = Integer.parseInt(st.nextToken());
			if (temp == 1)
				list.add(i);
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < list.size(); i++) {
			if (i + k - 1 >= list.size())
				break;
			int m = list.get(i + k - 1) - list.get(i) + 1;
			if (m < min)
				min = m;
		}
		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);

	}
}