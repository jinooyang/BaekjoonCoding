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
		int answer[] = new int[n];
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(i + 1);
		}
		int idx = 0;
		for (int i = 0; i < n; i++) {
			idx = (idx + k - 1) % list.size();
			int num = list.remove(idx);
			answer[i] = num;
		}
		System.out.print("<");
		for (int i = 0; i < n - 1; i++) {
			System.out.print(answer[i] + ", ");
		}
		System.out.println(answer[n - 1] + ">");
	}
}