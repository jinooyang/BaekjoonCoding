import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer> houseList = new ArrayList<>();

	static int answer = 0;
	static int c;
	static int size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		size = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			houseList.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(houseList);
		int max = houseList.get(houseList.size() - 1) - houseList.get(0);
		bSearch(1, max);
		System.out.println(answer);
	}

	private static void bSearch(int s, int e) {
		if (s > e)
			return;
		int mid = (s + e) / 2;
		int n = 0;
		int before = -1000000000;
		int cnt = 0;
		// System.out.println("mid " + mid);
		while (n < size) {
			int now = houseList.get(n++);
			// System.out.println("now : " + now);
			if (now - before >= mid) {
				before = now;
				cnt++;
			}
		}
		// System.out.println("cnt " + cnt);
		if (cnt >= c) {// 공유기를 예상보다 많이 설치할 수 있다. 범위를 넓혀보자
			answer = mid;
			bSearch(mid + 1, e);
		} else {// 공유기를 원하는만큼 설치할 수 없다. 범위를 좁히자
			bSearch(s, mid - 1);
		}
	}
}