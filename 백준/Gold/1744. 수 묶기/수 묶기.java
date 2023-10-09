import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		PriorityQueue<Integer> plus = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> minus = new PriorityQueue<>();
		int ones = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			if (x > 1)
				plus.add(x);
			else if (x == 1) {
				ones++;
			} else {
				minus.add(x);
			}
		}
		int answer = 0;
		while (plus.size() >= 2) {
			int a = plus.poll();
			int b = plus.poll();
			answer += a * b;
		}
		if (!plus.isEmpty()) {
			answer += plus.poll();
		}

		while (minus.size() >= 2) {
			int a = minus.poll();
			int b = minus.poll();
			answer += a * b;
		}
		if (!minus.isEmpty()) {
			answer += minus.poll();
		}
		System.out.println(answer+ones);
	}
}