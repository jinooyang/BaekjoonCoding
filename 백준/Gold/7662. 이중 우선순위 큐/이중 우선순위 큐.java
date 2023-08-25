import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
			PriorityQueue<Integer> minHeap = new PriorityQueue<>();
			Map<Integer, Integer> map = new HashMap<>();

			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				char c = st.nextToken().charAt(0);
				if (c == 'I') {// 삽입
					int x = Integer.parseInt(st.nextToken());
					maxHeap.add(x);
					minHeap.add(x);
					if (map.containsKey(x)) {
						map.put(x, map.get(x) + 1);
					} else {
						map.put(x, 1);
					}

				}
				if (c == 'D') {
					int x = Integer.parseInt(st.nextToken());
					PriorityQueue<Integer> pq = (x == 1 ? maxHeap : minHeap);
					while (!pq.isEmpty()) {
						if (map.get(pq.peek()) != 0)
							break;
						else// 맵을 보니까 이미 없는 값이면 빼버리고 다음 값을 보자
							pq.poll();
					}
					// peek가 있는 값임이 보장됨
					// 혹은 empty일수도있음
					if (!pq.isEmpty()) {
						int now = pq.poll();
						map.put(now, map.get(now) - 1);
					}

				}

			}
			boolean isEmpty = true;
			for (Integer i : map.keySet()) {
				if (map.get(i) > 0)
					isEmpty = false;
			}
			if (isEmpty) {
				sb.append("EMPTY\n");
				// System.out.println("EMPTY");
			} else {
				while (!maxHeap.isEmpty()) {
					if (map.get(maxHeap.peek()) != 0)
						break;
					else// 맵을 보니까 이미 없는 값이면 빼버리고 다음 값을 보자
						maxHeap.poll();
				}
				int max = maxHeap.peek();

				while (!minHeap.isEmpty()) {
					if (map.get(minHeap.peek()) != 0)
						break;
					else// 맵을 보니까 이미 없는 값이면 빼버리고 다음 값을 보자
						minHeap.poll();
				}
				int min = minHeap.peek();
				sb.append(max).append(" ").append(min).append("\n");
				// System.out.println(maxHeap.peek() + " " + minHeap.peek());
			}
		}
		System.out.println(sb.toString());
	}
}