import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Data implements Comparable<Data> {
		int key;
		int value;

		public Data(int key, int value) {
			super();
			this.key = key;
			this.value = value;
		}

		@Override
		public int compareTo(Data o) {

			return Integer.compare(this.key, o.key);
		}

	}

	static List<Data> list;
	static int k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());// 데이터수
		int m = Integer.parseInt(st.nextToken());// 명령 횟수
		k = Integer.parseInt(st.nextToken());// 거리의 제한
		list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int key = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			list.add(new Data(key, value));
		}
		Collections.sort(list);
		// Collections.sort(list);
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			// 1 Key Value : 해당 Key와 Value를 가진 데이터를 추가한다. Key가 이미 존재하는 입력은 주어지지 않는다.
			if (command == 1) {
				int key = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				int idx = find(0, list.size() - 1, key, true);

				list.add(idx, new Data(key, value));

			}
			// 2 Key Value : 해당 Key로 검색된 데이터를 Value로 변경한다. 조건을 만족하는 유일한 Key가 없는 경우 무시한다.
			if (command == 2) {

				int key = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				int closestKey = find(0, list.size() - 1, key, false);// 가장 근접한 키의 인덱스를 받는다

				if (closestKey >= 0)
					list.get(closestKey).value = value;

			}
			// 3 Key : 해당 Key로 검색된 데이터를 출력한다. 조건을 만족하는 Key가 없는 경우 -1을 출력한다. 만약 해당하는 Key가 두 개
			// 이상 존재한다면 ?를 출력한다. 모든 출력은 개행을 포함해야 한다.
			if (command == 3) {

				int key = Integer.parseInt(st.nextToken());
				int closestKey = find(0, list.size() - 1, key, false);// 가장 근접한 키의 인덱스를 받는다
				// System.out.println(closestKey + " is closest to " + key);
				if (closestKey == -1)
					sb.append(-1).append("\n");
				else if (closestKey == -2)
					sb.append("?\n");
				else {
					sb.append(list.get(closestKey).value).append("\n");
				}
			}
		}
		System.out.println(sb.toString());
	}

	private static int find(int start, int end, int key, boolean sort) {
		// System.out.println("start : " + start);
		// System.out.println("end : " + end);
		// 가장 근접한 키의 인덱스를 반환한다
		// 조건을 만족하지 않으면 -1을 반환한다
		// 조건을 만족하는게 여러개면 -2를 반환한다
//		if (start > end)
//			return -1;
		if (start == end) {
			if (list.get(start).key == key)
				return start;
		}
		if (start + 1 == end) {
			// System.out.println("start : " + start);
			// System.out.println("end : " + end);
			if (sort)
				return end;
			int a = Math.abs(list.get(start).key - key);
			int b = Math.abs(list.get(end).key - key);
			// System.out.println("kk" + a + " " + b);
			if (a > k && b > k) {

				return -1;
			}
			if (a == b) {

				return -2;
			}
			if (a < b)

				return start;
			if (b < a)
				return end;
		}

		int mid = (start + end) / 2;
		// System.out.println(list.get(mid).key + " " + key);
		if (list.get(mid).key == key)
			return mid;
		if (key > list.get(mid).key) {
			return find(mid, end, key, sort);
		} else {
			return find(start, mid, key, sort);
		}

	}
}