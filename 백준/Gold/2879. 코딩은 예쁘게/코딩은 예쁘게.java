import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		List<Integer> listA = new ArrayList<>();
		List<Integer> listB = new ArrayList<>();
		List<Integer> diff = new ArrayList<>();
		//첫 줄 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			listA.add(Integer.parseInt(st.nextToken()));
		}
		//둘째 줄 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			listB.add(Integer.parseInt(st.nextToken()));
		}

		for (int i = 0; i < n; i++) {
			diff.add(listB.get(i) - listA.get(i));
		}

		Queue<List<Integer>> q = new ArrayDeque<>();
		Queue<Integer> qMin = new ArrayDeque<>();
		addToQueue(n, diff, q, qMin);
		// System.out.println(q);
		// System.out.println(qMin);
		int answer = 0;
		// int cnt = 0;

		while (!q.isEmpty() ) {
			// cnt++;
			List<Integer> now = q.poll();
			// System.out.println(now.toString());
			int min = qMin.poll();
			answer += min;
			for (int i = 0; i < now.size(); i++) {
				if (now.get(i) < 0)
					now.set(i, now.get(i) + min);//값을 다 빼준다
				else
					now.set(i, now.get(i) - min);
			}
			addToQueue(now.size(), now, q, qMin);
		}
		System.out.println(answer);

	}

	private static void addToQueue(int listSize, List<Integer> list, Queue<List<Integer>> q, Queue<Integer> qMin) {
		List<Integer> tempList = new ArrayList<>();
		int tempMin = Integer.MAX_VALUE;

		for (int i = 0; i < listSize; i++) {
			//0이고 넣을게 있으면 넣는다
			if (list.get(i) == 0 && tempList.size() > 0) {
				q.add(new ArrayList<>(tempList));
				qMin.add(tempMin);
				tempMin = Integer.MAX_VALUE;
				tempList.clear();

				continue;
			}
			//등호가 바꼈을 경우
			if (i > 0 && list.get(i) * list.get(i - 1) < 0 && tempList.size() > 0) {
				q.add(new ArrayList<>(tempList));
				qMin.add(tempMin);
				tempMin = Integer.MAX_VALUE;
				tempList.clear();

			}
			if (list.get(i) != 0) {
				tempList.add(list.get(i));
				tempMin = Math.min(tempMin, Math.abs(list.get(i)));
			}

		}
		if (tempList.size() > 0) {
			q.add(new ArrayList<>(tempList));
			qMin.add(tempMin);
			tempMin = Integer.MAX_VALUE;
		}
	}

}