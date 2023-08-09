import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		// int T = 10;

		int deli[] = { 0, 0, 1, -1 };
		int delj[] = { 1, -1, 0, 0 };

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int ary[][] = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					ary[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			boolean visited[][] = new boolean[n][n];
			int maxcnt = 0;
			int maxi = 0;
			int maxj = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {

					if (!visited[i][j]) {// 방문한적 없는 노드면 bfs실행
						Queue<Integer> qi = new ArrayDeque<>();
						Queue<Integer> qj = new ArrayDeque<>();
						Queue<Integer> qcnt = new ArrayDeque<>();
						qi.add(i);
						qj.add(j);
						qcnt.add(1);
						visited[i][j] = true;
						while (!qi.isEmpty()) {
							// 큐에서 값 빼기
							int nowi = qi.poll();
							int nowj = qj.poll();
							int cnt = qcnt.poll();
							if (cnt > maxcnt) {// 최고 갱신
								maxcnt = cnt;
								maxi = i;
								maxj = j;
							}
							if (cnt == maxcnt) {// 최고가 같으면 시작이 작은 곳으로 갱신
								if (ary[i][j] < ary[maxi][maxj]) {
									maxi = i;
									maxj = j;
								}
							}
							for (int idx = 0; idx < 4; idx++) {
								int di = nowi + deli[idx];
								int dj = nowj + delj[idx];
								// 범위 안에 있고 1크면 큐에 추가
								if (di >= 0 && dj >= 0 && di < n && dj < n && ary[di][dj] - 1 == ary[nowi][nowj]) {
									qi.add(di);
									qj.add(dj);
									qcnt.add(cnt + 1);
								}

							}
						}
					}
				}
			}
			
			System.out.println("#" + test_case + " " + ary[maxi][maxj] + " " + maxcnt);
			
		}
	}
}