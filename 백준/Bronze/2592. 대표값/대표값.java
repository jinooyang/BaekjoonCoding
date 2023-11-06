import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int avg = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			int x = Integer.parseInt(br.readLine());
			if (!map.containsKey(x))
				map.put(x, 0);
			map.put(x, map.get(x) + 1);
			avg+=x;
		}
		avg = avg/10;
		int maxcnt = 0;
		int ans = 0;
		for(Integer i : map.keySet()) {
			int cnt = map.get(i);
			if(cnt > maxcnt) {
				ans = i;
				maxcnt = cnt;
			}
		}
		System.out.println(avg);System.out.println(ans);

	}
}
