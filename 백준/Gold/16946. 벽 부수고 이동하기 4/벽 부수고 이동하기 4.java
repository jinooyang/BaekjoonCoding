import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int i;
		int j;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int map[][][] = new int[n][m][2];
		// map[][][0] = 입력 받은 맵
		// map[][][1] = 영역 번호 저장
		boolean visited[][] = new boolean[n][m];// 노드를 방문했는지 판단하는 배열
		int deli[] = { 0, 0, 1, -1 };
		int delj[] = { 1, -1, 0, 0 };
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < m; j++) {
				map[i][j][0] = s.charAt(j) - '0';
				if (map[i][j][0] == 1)
					visited[i][j] = true;
			}
		}
		List<Integer> areaSizeList = new ArrayList<>();
		areaSizeList.add(-1);// 인덱스 맞추기 위한 더미 값
		int areaNumber = 0;// 영역 번호는 1부터 시작
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j]) {// 방문 하지 않은 노드, 벽은 방문한 노드로 입력 때 저장했음
					// bfs
					areaNumber++;
					Queue<Node> q = new ArrayDeque<>();
					q.add(new Node(i, j));
					visited[i][j] = true;
					map[i][j][1] = areaNumber;
					int areaSize = 1;
					while (!q.isEmpty()) {
						Node node = q.poll();
						for (int idx = 0; idx < 4; idx++) {
							int di = deli[idx] + node.i;
							int dj = delj[idx] + node.j;
							if (di >= 0 && dj >= 0 && di < n && dj < m && !visited[di][dj]) {// 범위 내에 있고 방문 하지 않았다면
								q.add(new Node(di, dj));// bfs 큐에 노드 저장
								areaSize++;// 영역 크기 증가
								visited[di][dj] = true;// 방문 표시한다
								map[di][dj][1] = areaNumber;// 영역 번호를 저장한다
							}
						}

					}
					// bfs끝나면 해당 영역의 크기를 리스트에 저장한다
					areaSizeList.add(areaSize);
				}
			}
		}

//		// 영역 번호 출력
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				System.out.print(map[i][j][1]);
//			}
//			System.out.println();
//		}
//		// 영역 크기 출력
//		for (int i = 1; i < areaSizeList.size(); i++) {
//			System.out.println(i + "area size : " + areaSizeList.get(i));
//		}

		// 각 벽의 상하좌우를 확인해서 영역 번호가 다른 것들의 크기를 모두 더한다
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j][0] == 1) {// 벽일 경우
					Set<Integer> set = new HashSet<>();// 해당 벽의 주변 영역 번호를 저장하자
					for (int idx = 0; idx < 4; idx++) {
						int di = i + deli[idx];
						int dj = j + delj[idx];
						if (di >= 0 && dj >= 0 && di < n && dj < m && map[di][dj][0] == 0) {// 범위 안에 있고 벽이 아닌경우
							set.add(map[di][dj][1]);
						}
					}
					// set에 있는 영역 번호에 해당하는 크기를 map[][][0]에 저장하자
					// 최종 출력 : map[][][0]
					for (Integer areaNum : set) {
						map[i][j][0] += areaSizeList.get(areaNum);
					}
				}
			}
		}

//		System.out.println("-----map-----");
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<m;j++) {
//				System.out.print(map[i][j][0]+ " ");
//			}
//			System.out.println();
//		}
//		System.out.println("-------------");
		// 최종 출력
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				bw.write((map[i][j][0] % 10) + "");
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}
}