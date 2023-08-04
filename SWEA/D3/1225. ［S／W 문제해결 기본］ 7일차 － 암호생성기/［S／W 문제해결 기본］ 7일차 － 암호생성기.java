import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int test_case = 1; test_case <= 10; test_case++) {

			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			Queue<Integer> q = new ArrayDeque<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				int temp = Integer.parseInt(st.nextToken());
				q.add(temp);
			}
			int decrease = 0;
			while (true) {
				int front = q.poll();
				front = front - (decrease % 5 + 1);
				if (front <= 0) {
					front = 0;
					q.add(front);
					break;
				}

				q.add(front);
//				System.out.print(decrease + "만큼 뺌 : ");
//				for (int x = 0; x < 8; x++) {
//					int num = q.poll();
//					System.out.print(num + " ");
//					q.add(num);
//
//				}
//				System.out.println();
				decrease++;
			}

			System.out.print("#" + T + " ");
			for (int i = 0; i < 8; i++) {
				System.out.print(q.poll() + " ");
			}
			System.out.println();

		}

	}
}