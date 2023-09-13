import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int deli[] = { 0, 0, 1, -1 };
	static int delj[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		int ary[][] = new int[n][m];
		for (int t = 0; t < a; t++) {
			st = new StringTokenizer(br.readLine());
			int j1 = Integer.parseInt(st.nextToken());
			int i1 = Integer.parseInt(st.nextToken());
			int j2 = Integer.parseInt(st.nextToken());
			int i2 = Integer.parseInt(st.nextToken());
			for (int i = i1; i < i2; i++) {
				for (int j = j1; j < j2; j++) {
					ary[i][j] = 1;
				}
			}
		}
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (ary[i][j] == 0) {
					int area = 1;
					Queue<Integer> qi = new ArrayDeque<>();
					Queue<Integer> qj = new ArrayDeque<>();
					qi.add(i);
					qj.add(j);
					ary[i][j] = 1;
					while (!qi.isEmpty()) {
						int nowi = qi.poll();
						int nowj = qj.poll();
						for (int idx = 0; idx < 4; idx++) {
							int di = nowi + deli[idx];
							int dj = nowj + delj[idx];
							if (di >= 0 && dj >= 0 && di < n && dj < m && ary[di][dj] == 0) {
								ary[di][dj] = 1;
								area++;
								qi.add(di);
								qj.add(dj);
							}

						}
					}
					list.add(area);
				}
			}
		}
		Collections.sort(list);
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
	}
}