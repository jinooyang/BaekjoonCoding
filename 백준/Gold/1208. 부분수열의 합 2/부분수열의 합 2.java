import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int ary[];
	static long answer = 0;
	static int s;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		ary = new int[n];
		for (int i = 0; i < n; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}
		if (n == 1 && s == ary[0]) {
			System.out.println(1);
			return;
		}
		int mid = n / 2;
		// 절반 잘라서 가능한 조합 모두 구한다
		// 조합끼리 붙여서 s가 되는 수를 센다
		// 앞쪽, 혹은 뒤쪽에서 이미 s가되는 경우를 센다
		// 0부터mid까지모든 합 경우를 찾늗다
		// mid+1부터 n-1까지 모든 합 찾는다

		List<Integer> list_front = new ArrayList<>();
		List<Integer> list_back = new ArrayList<>();
		Map<Integer, Integer> back = new HashMap<>();

		getCombo(list_front, 0, mid, 0, 0, null);
		getCombo(list_back, mid + 1, n - 1, 0, 0, back);
		//System.out.println(answer);
		for (int i = 0; i < list_front.size(); i++) {
			int x = list_front.get(i);

			int find = s - x;

			if (!back.containsKey(find))
				continue;
			int cnt = back.get(find);
			//System.out.println(x + " " + find);
			answer += cnt;
		}
		System.out.println(answer);
	}

	private static void getCombo(List<Integer> list, int start, int end, int sum, int cnt, Map<Integer, Integer> back) {
		if (start > end) {
			if (cnt > 0) {
				list.add(sum);
				if (sum == s) {
					//System.out.println("sum is  : " + sum);
					answer++;
				}
				if (back != null) {
					if (!back.containsKey(sum)) {
						back.put(sum, 0);
					}
					back.put(sum, back.get(sum) + 1);
				}
			}
			return;
		}
		getCombo(list, start + 1, end, sum + ary[start], cnt + 1, back);
		getCombo(list, start + 1, end, sum, cnt, back);
	}
}