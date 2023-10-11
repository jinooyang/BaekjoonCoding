import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> minPQ = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			int x = Integer.parseInt(br.readLine());
			int big = Integer.MAX_VALUE;
			if (!minPQ.isEmpty()) {
				big = minPQ.peek();
			}

			if (x < big) {
				maxPQ.add(x);
				while (maxPQ.size() > minPQ.size() + 1)
					minPQ.add(maxPQ.poll());
			} else {
				maxPQ.add(minPQ.poll());
				minPQ.add(x);
				while (maxPQ.size() > minPQ.size() + 1)
					minPQ.add(maxPQ.poll());
			}
//			System.out.println(maxPQ.toString() + " " + minPQ.toString());
			sb.append(maxPQ.peek()).append("\n");
		}
		System.out.println(sb);
	}
}