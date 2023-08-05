import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());// 방
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());// 행동
		List<Integer> rooms = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			rooms.add(i);
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for (int idx = rooms.size() - 1; idx >= 0; idx--) {
				if (rooms.get(idx) <= y && rooms.get(idx) > x) {
					rooms.remove(idx);
				}
			}

		}
		System.out.println(rooms.size() - 1);
	}
}