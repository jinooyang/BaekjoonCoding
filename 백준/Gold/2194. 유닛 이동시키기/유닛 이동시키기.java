import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int map[][];
	static int deli[] = { 0, 0, 1, -1 };
	static int delj[] = { 1, -1, 0, 0 };
	static int n;
	static int m;
	static int a;
	static int b;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());// 맵 크기
		m = Integer.parseInt(st.nextToken());// 맵 크기
		a = Integer.parseInt(st.nextToken());// 유닛
		b = Integer.parseInt(st.nextToken());// 유닛
		int k = Integer.parseInt(st.nextToken());// 장애물 개수
		map = new int[n][m];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			map[y][x] = 1;// 장애물로 표시
		}
		st = new StringTokenizer(br.readLine());
		int starti = Integer.parseInt(st.nextToken()) - 1;
		int startj = Integer.parseInt(st.nextToken()) - 1;
		st = new StringTokenizer(br.readLine());
		int endi = Integer.parseInt(st.nextToken()) - 1;
		int endj = Integer.parseInt(st.nextToken()) - 1;

		Queue<Integer> qi = new ArrayDeque<>();
		Queue<Integer> qj = new ArrayDeque<>();
		Queue<Integer> qcnt = new ArrayDeque<>();
		boolean visited[][] = new boolean[n][m];
		qi.add(starti);
		qj.add(startj);
		qcnt.add(0);
		visited[starti][startj] = true;
		int answer = -1;
		while (!qi.isEmpty()) {
			int i = qi.poll();
			int j = qj.poll();
			int cnt = qcnt.poll();
			if (i == endi && j == endj) {
				answer = cnt;
				break;
			}

			for (int idx = 0; idx < 4; idx++) {
				int di = i + deli[idx];
				int dj = j + delj[idx];
				if (check(di, dj) && !visited[di][dj]) {
					visited[di][dj] = true;
					qi.add(di);
					qj.add(dj);
					qcnt.add(cnt + 1);
				}
			}

		}
		System.out.println(answer);

	}

	private static boolean check(int di, int dj) {// 범위 안에 있고 벽이 아닌지 확인하자
		boolean result = true;
		for (int i = di; i < di + a; i++) {
			for (int j = dj; j < dj + b; j++) {
				if (i >= 0 && j >= 0 && i < n && j < m && map[i][j] == 0) {
					continue;
				} else {
					result = false;
					break;
				}
			}
		}
		return result;
	}
}