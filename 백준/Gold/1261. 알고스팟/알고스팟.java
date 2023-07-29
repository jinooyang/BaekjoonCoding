import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int ary[][] = new int[m][n];
		Integer checked[][] = new Integer[m][n];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < n; j++) {
				ary[i][j] = s.charAt(j) - '0';
				checked[i][j] = 100000;
			}
		}
//		
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				System.out.print(ary[i][j]);
//			}
//			System.out.println();
//		}
		int deli[] = { 0, 0, 1, -1 };
		int delj[] = { 1, -1, 0, 0 };

		Queue<Integer> qi = new LinkedList<>();
		Queue<Integer> qj = new LinkedList<>();
		Queue<Integer> qcnt = new LinkedList<>();

		qi.add(0);
		qj.add(0);
		qcnt.add(0);
		checked[0][0] = 0;
		int answer = 0;
		int k = 0;
		while (true) {
			// System.out.println("KK:" + k++);
			if (qi.isEmpty()) {
				break;
			}
			int i = qi.poll();
			int j = qj.poll();
			int cnt = qcnt.poll();

			for (int x = 0; x < 4; x++) {
				int di = i + deli[x];
				int dj = j + delj[x];
				// 상하 좌우 중 0 인 곳을 우선으로 방문해야함
				if (di >= 0 && dj >= 0 && di < m && dj < n) {

					if (ary[di][dj] == 1) {//벽일경우
						if (checked[di][dj] > (cnt + 1)) {
							qi.add(di);
							qj.add(dj);
							qcnt.add(cnt + 1);
							checked[di][dj] = (cnt + 1);
						}
					} else {//벽이 아닐 경우
						if (checked[di][dj] > (cnt)) {
							qi.add(di);
							qj.add(dj);
							qcnt.add(cnt);
							checked[di][dj] = (cnt);

						}
					}
				}
				
			}
		}
		
//		for(int i=0;i<m;i++) {
//			for(int j=0;j<n;j++) {
//				System.out.print(checked[i][j]);
//			}
//			System.out.println();
//		}
		System.out.println(checked[m-1][n-1]);
	}
}