import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int start;
		int end;

		public Node(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Node o) {
			if (this.start == o.start) {
				return Integer.compare(this.end, o.end);
			}
			return Integer.compare(this.start, o.start);
		}

	}

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		List<Node> list = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			list.add(new Node(s, e));

		}
		Collections.sort(list);

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(0);
		for (int i = 0; i < n; i++) {
			int s = list.get(i).start;
			int e = list.get(i).end;
			int fastestEnd = pq.peek();
			if (s >= fastestEnd) {
				pq.poll();
				pq.add(e);
			} else {
				pq.add(e);
			}

		}
		System.out.println(pq.size());
	}
}