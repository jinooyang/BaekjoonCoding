import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		char regularMap[][] = new char[n][n];
		char colorBlindMap[][] = new char[n][n];
		boolean regularCheck[][] = new boolean[n][n];
		boolean colorBlindCheck[][] = new boolean[n][n];
		int deli[] = { 0, 0, 1, -1 };
		int delj[] = { 1, -1, 0, 0 };
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < n; j++) {
				char temp = s.charAt(j);
				regularMap[i][j] = temp;
				if (temp == 'R' || temp == 'G')
					temp = 'A';
				colorBlindMap[i][j] = temp;
			}
		}

		Queue<Integer> qi = new ArrayDeque<>();
		Queue<Integer> qj = new ArrayDeque<>();
		int regAnswer = 0;
		for (int starti = 0; starti < n; starti++) {
			for (int startj = 0; startj < n; startj++) {
				if (!regularCheck[starti][startj]) {// 방문을 하지 않은 노드를 만났을 때
					regAnswer++;
					qi.add(starti);
					qj.add(startj);
					regularCheck[starti][startj] = true;
					char color = regularMap[starti][startj];
					while (!qi.isEmpty()) {
						int i = qi.poll();
						int j = qj.poll();
						// 자식을 큐에 넣자
						for (int idx = 0; idx < 4; idx++) {
							int di = i + deli[idx];
							int dj = j + delj[idx];
							if (di >= 0 && dj >= 0 && di < n && dj < n && regularMap[di][dj] == color
									&& !regularCheck[di][dj]) {
								qi.add(di);
								qj.add(dj);
								regularCheck[di][dj] = true;
							}
						}
					}
				}
			}
		}
		System.out.print(regAnswer + " ");
		int colorBlindAnswer = 0;
		for (int starti = 0; starti < n; starti++) {
			for (int startj = 0; startj < n; startj++) {
				if (!colorBlindCheck[starti][startj]) {// 방문을 하지 않은 노드를 만났을 때
					colorBlindAnswer++;
					qi.add(starti);
					qj.add(startj);
					colorBlindCheck[starti][startj] = true;
					char color = colorBlindMap[starti][startj];
					while (!qi.isEmpty()) {
						int i = qi.poll();
						int j = qj.poll();
						// 자식을 큐에 넣자
						for (int idx = 0; idx < 4; idx++) {
							int di = i + deli[idx];
							int dj = j + delj[idx];
							if (di >= 0 && dj >= 0 && di < n && dj < n && colorBlindMap[di][dj] == color
									&& !colorBlindCheck[di][dj]) {
								qi.add(di);
								qj.add(dj);
								colorBlindCheck[di][dj] = true;
							}
						}
					}
				}
			}
		}
		System.out.println(colorBlindAnswer + " ");

	}

}