import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int test_case = 1; test_case <= 10; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int dump = Integer.parseInt(st.nextToken());
			List<Integer> list = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 100; i++) {
				list.add(Integer.parseInt(st.nextToken()));

			}

			System.out.println();
			for (int i = 0; i < dump; i++) {
				Collections.sort(list);
				int min = list.get(0);
				int max = list.get(list.size() - 1);

				list.set(0, max - 1);
				list.set(list.size() - 1, min + 1);

			}
			Collections.sort(list);
			int answer = list.get(list.size() - 1) - list.get(0);
			System.out.println("#" + test_case + " " + answer);

		}
	}
}