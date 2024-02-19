import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		String s = br.readLine();
		List<List<Integer>> list = new ArrayList<>();
		List<List<Boolean>> blist = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			list.add(new ArrayList<>());
			blist.add(new ArrayList<>());

		}
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			list.get(u).add(v);
			blist.get(u).add(false);
			list.get(v).add(u);
			blist.get(v).add(false);
		}
		Queue<Integer> q = new ArrayDeque<>();
		q.add(1);
		boolean visited[] = new boolean[n + 1];
		visited[1] = true;
		while (!q.isEmpty()) {
			int now = q.poll();
			for (int i = 0; i < list.get(now).size(); i++) {
				if (!visited[list.get(now).get(i)]) {
					//방문한적없으면
					visited[list.get(now).get(i)] = true;
					blist.get(now).set(i, true);
					q.add(list.get(now).get(i));
				}
			}
		}
		//누가 자식인지 표시해줌
		for (int i = 1; i < n + 1; i++) {
			for (int j = list.get(i).size() - 1; j >= 0; j--) {
				if (blist.get(i).get(j) == false) {
					blist.get(i).remove(j);
					list.get(i).remove(j);
				}
			}
			Collections.sort(list.get(i), (o1, o2) -> {
				return -1 * Integer.compare(s.charAt(o1 - 1), s.charAt(o2 - 1));
			});
			if (list.get(i).size() > 0) {
				char max = s.charAt(list.get(i).get(0) - 1);
				for (int j = list.get(i).size() - 1; j >= 0; j--) {
					if (s.charAt(list.get(i).get(j) - 1) < max) {
						list.get(i).remove(j);
					}
				}
			}
		}
		// System.out.println(list);
		Deque<Integer> qNum = new ArrayDeque<>();
		Deque<Integer> qDepth = new ArrayDeque<>();
		qNum.add(1);
		qDepth.add(1);
		List<Integer> depthList = new ArrayList<>();
		depthList.add(0);
		StringBuilder answer = new StringBuilder();
		while (!qNum.isEmpty()) {
			//현재 위치
			int now = qNum.poll();
			// System.out.println(now);
			//현재 뎁스
			int depth = qDepth.poll();
			if (answer.length() < depth)
				answer.append(s.charAt(now - 1));
			else {
				answer.replace(depth - 1, depth, String.valueOf(s.charAt(now - 1)));
			}
			//같은 뎁스에서 나보다 작은애들은 큐에서 모두 제외한다
			for (Integer i : list.get(now)) {
				//자식들을 큐에 넣는데
				// 근데 넣기 전에 나보다 작은애들은 다 뺀다
				//근데 나보다 큰게 있으면 넣지 않는다
				boolean possible = true;
				while (!qDepth.isEmpty()) {
					if (depth + 1 == qDepth.getLast()) {
						if (s.charAt(qNum.getLast() - 1) < s.charAt(i - 1)) {
							qNum.poll();
							qDepth.poll();

						} else if (s.charAt(qNum.getLast() - 1) == s.charAt(i - 1)) {
							break;
						} else {
							possible = false;
							break;
						}
					} else
						break;
				}
				if (possible) {
					qNum.add(i);
					qDepth.add(depth + 1);
				}
			}
		}
		System.out.println(answer);

	}

}