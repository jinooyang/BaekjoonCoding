import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		String password = st.nextToken();
		st = new StringTokenizer(br.readLine());
		int each[] = new int[4];
		for (int i = 0; i < 4; i++) {
			each[i] = Integer.parseInt(st.nextToken());
		}
		// 계산
		Queue<Character> q = new LinkedList<>();
		Map<Character, Integer> map = new HashMap<>();
		map.put('A', 0);
		map.put('C', 0);
		map.put('G', 0);
		map.put('T', 0);
		int answer = 0;
		for (int i = 0; i < s; i++) {
			char nextChar = password.charAt(i);
			q.add(nextChar);
			map.put(nextChar, map.get(nextChar) + 1);
			if (q.size() > p) {
				char removedChar = q.poll();
				map.put(removedChar, map.get(removedChar) - 1);
			}
			if (q.size() == p) {
				if (map.get('A') >= each[0] && map.get('C') >= each[1] && map.get('G') >= each[2]
						&& map.get('T') >= each[3]) {
					answer++;
				}
			}
		}
		// 출력
		System.out.println(answer);

	}
}