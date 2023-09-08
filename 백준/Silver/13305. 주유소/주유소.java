import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int price;
		int min;

		public Node(int price, int min) {
			super();
			this.price = price;
			this.min = min;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int dist[] = new int[n - 1];
		int price[] = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n - 1; i++) {
			dist[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			price[i] = Integer.parseInt(st.nextToken());
		}
		Node dp[] = new Node[n];
		dp[0] = new Node(0, price[0]);
		for (int i = 1; i < n; i++) {
			int curPrice = price[i];
			dp[i] = new Node(dp[i - 1].min * dist[i - 1] + dp[i - 1].price, Math.min(dp[i - 1].min, curPrice));
		}
		System.out.println(dp[n - 1].price);
	}
}