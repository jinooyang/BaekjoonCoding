import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine());
		// int T = Integer.parseInt(st.nextToken());
		StringTokenizer st = null;
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			int commands = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < commands; i++) {
				char commandStart = st.nextToken().charAt(0);
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				List<Integer> tempList = new ArrayList<>();
				for (int j = 0; j < y; j++) {
					int s = Integer.parseInt(st.nextToken());
					tempList.add(s);
				}
				list.addAll(x, tempList);
			}
			System.out.print("#" + test_case + " ");
			for (int i = 0; i < 10; i++) {
				System.out.print(list.get(i) + " ");
			}
			System.out.println();
		}
	}
}