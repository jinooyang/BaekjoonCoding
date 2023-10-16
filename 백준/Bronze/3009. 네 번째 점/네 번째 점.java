import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		Set<Integer> x = new HashSet<>();
		Set<Integer> y = new HashSet<>();

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (x.contains(a))
				x.remove(a);
			else
				x.add(a);
			if (y.contains(b))
				y.remove(b);
			else
				y.add(b);

		}
		for(Integer i : x) {
			System.out.print(i + " ");
		}
		for(Integer i : y) {
			System.out.print(i + " ");
		}
	}
}