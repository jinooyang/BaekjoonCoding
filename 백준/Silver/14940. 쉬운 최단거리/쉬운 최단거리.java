import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int ary[][] = new int[n][m];
		int check[][] = new int[n][m];
		int starti = 0;
		int startj = 0;
		Queue<Integer> qi = new LinkedList<>();
		Queue<Integer> qj = new LinkedList<>();
		Queue<Integer> qcnt = new LinkedList<>();
		int deli[] = { 0, 0, 1, -1 };// 우, 좌, 하, 상
		int delj[] = { 1, -1, 0, 0 };
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
				if (ary[i][j] == 2) {
					starti = i;
					startj = j;
				}
			}
		}
		// bfs
		qi.add(starti);
		qj.add(startj);
		qcnt.add(0);
		check[starti][startj] = -1;

		while (true) {
			if (qi.isEmpty())
				break;
			int i = qi.poll();
			int j = qj.poll();
			int cnt = qcnt.poll();

			for (int x = 0; x < 4; x++) {
				int di = i + deli[x];
				int dj = j + delj[x];
				// 범위 안에 있고, 갈 수 있는 지역이고, 방문한적이 없는 지역
				if (di >= 0 && dj >= 0 && di < n && dj < m && ary[di][dj] != 0 && check[di][dj] == 0) {
					qi.add(di);
					qj.add(dj);
					qcnt.add(cnt + 1);
					check[di][dj] = cnt + 1;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == starti && j == startj) {
					bw.write("0 ");
					continue;
				}
				if (check[i][j] == 0 && ary[i][j] != 0) {
					bw.write("-1 ");
				} else
					bw.write(check[i][j] + " ");
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();

	}
}