import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		List<Set<Integer>> set = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			set.add(new TreeSet<>());
		}

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				int x = Integer.parseInt(st.nextToken());
				set.get(i).add(x);
				set.get(x).add(i);
			}
		}

		// System.out.println(set);
		Set<Integer> blue = new TreeSet<>();
		Set<Integer> white = new TreeSet<>();
		Queue<Integer> q = new ArrayDeque<>();
		Queue<Boolean> qTeam = new ArrayDeque<>();

		// white.addAll(set.get(1));//1이 싫어하는 사람들을 백팀에 넣는다

		boolean[] visited = new boolean[n + 1];

		for (int i = 1; i <= n; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			q.add(i);
			blue.add(i);//초기에는 1을 청팀에 넣는다
			qTeam.add(false);//false은 청, true은 백
			while (!q.isEmpty()) {
				int now = q.poll();
				// System.out.println(now);
				boolean team = qTeam.poll();

				//now가 싫어하는 사람들을 반대팀으로 몰아 넣고 큐에 추가한다
				for (Integer no : set.get(now)) {
					if (!visited[no]) {
						visited[no] = true;
						q.add(no);
						qTeam.add(!team);//반대팀으로 보낸다
						//팀에 추가를 한다
						if (!team)
							white.add(no);
						else
							blue.add(no);
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(blue.size()).append("\n");
		for (Integer b : blue) {
			sb.append(b).append(" ");
		}
		sb.append("\n");
		sb.append(white.size()).append("\n");
		for (Integer b : white) {
			sb.append(b).append(" ");
		}
		System.out.println(sb);
	}

}