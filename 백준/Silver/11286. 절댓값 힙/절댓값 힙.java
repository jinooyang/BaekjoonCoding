import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Num implements Comparable<Num> {
		int x;

		public Num(int x) {
			super();
			this.x = x;
		}

		@Override
		public int compareTo(Num o) {
			// TODO Auto-generated method stub
			if (Math.abs(this.x) != Math.abs(o.x))
				return Integer.compare(Math.abs(this.x), Math.abs(o.x));
			else
				return Integer.compare(this.x, o.x);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		PriorityQueue<Num> pq = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			if (num != 0)
				pq.add(new Num(num));
			if (num == 0) {
				int ans = 0;
				Num x = null;
				if (!pq.isEmpty()) {
					x = pq.poll();
					ans = x.x;
				}
				System.out.println(ans);
			}
		}
	}
}