import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Integer N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		if (N < 10 || (N < 100 && N % 10 == 0)) {
			System.out.println(-1);
			return;
		}

		int size = N.toString().length();
		Queue<Integer> q = new ArrayDeque<>();
		Queue<Integer> qcnt = new ArrayDeque<>();
		Map<Integer, Integer> map = new HashMap<>();
		q.add(N);
		qcnt.add(0);
		int answer = 0;

		while (!q.isEmpty()) {
			int now = q.poll();
			int cnt = qcnt.poll();
			// System.out.println(now + " " + cnt);

			if (cnt == K) {
				answer = Math.max(answer, now);
				continue;
			}
			boolean added = false;
			for (int i = 0; i < size - 1; i++) {
				for (int j = i + 1; j < size; j++) {
					int next = getNext(size, i, now, j);
					if (next >= now && (!map.containsKey(next) || cnt + 1 > map.get(next))) {
						map.put(next, cnt + 1);
						q.add(next);
						qcnt.add(cnt + 1);
						added = true;
					}
				}
			}
			if(!added){
				for (int i = 0; i < size - 1; i++) {
					for (int j = i + 1; j < size; j++) {
						int next = getNext(size, i, now, j);
						if (next < now && (!map.containsKey(next) || cnt + 1 > map.get(next))) {
							map.put(next, cnt + 1);
							q.add(next);
							qcnt.add(cnt + 1);
							added = true;
						}
					}
				}
			}

		}

		System.out.println(answer);

	}

	private static int getNext(int size, int i, int now, int j) {
		int a = cal(size, i, i, now);
		int b = cal(size, j, j, now);

		int c = cal(size, j, i, now);
		int d = cal(size, i, j, now);

		int next = now - a - b + c + d;
		return next;
	}

	private static int cal(int size, int i, int j, int now) {
		return (int)Math.pow(10, size - i - 1) * (now / (int)Math.pow(10, size - j - 1) % 10);
	}

}
