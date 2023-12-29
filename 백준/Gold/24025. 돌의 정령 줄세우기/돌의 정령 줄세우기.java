import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

//	static class Node implements Comparable<Node> {
//		int num;
//		int index;
//
//		public Node(int num, int index) {
//			super();
//			this.num = num;
//			this.index = index;
//		}
//
//		@Override
//		public int compareTo(Node o) {
//			if (this.num == o.num)
//				return Integer.compare(this.index, o.index);
//			else
//				return Integer.compare(o.num, this.num);
//		}
//
//		@Override
//		public String toString() {
//			return "Node [num=" + num + ", index=" + index + "]";
//		}
//	}

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int max = n;
		int min = 1;
		int answer[] = new int[n];
		st = new StringTokenizer(br.readLine());
		boolean isAnswer = true;
		for (int i = 0; i < n; i++) {
			int now = Integer.parseInt(st.nextToken());
			if (now > 0) {
				answer[i] = max--;
			}
			if (now < 0) {
				answer[i] = min++;
				if (i == n - 1)
					isAnswer = false;
			}

		}
		if (isAnswer) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < n; i++) {
				sb.append(answer[i]).append(" ");
			}
			System.out.println(sb);
		} else
			System.out.println(-1);

	}

}