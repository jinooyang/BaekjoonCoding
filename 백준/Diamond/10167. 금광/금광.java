import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;

	static class Node {
		//구간의 합을 나타낸다
		long sum;
		//구간의 왼쪽 끝 최대합을 나타낸다
		long leftMax;

		//구간의 오른쪽 끝 최대합을 나타낸다
		long rightMax;

		//구간의 최대합구간을 나타낸다
		long max;

		public Node(long sum, long leftMax, long rightMax, long max) {
			this.sum = sum;
			this.leftMax = leftMax;
			this.rightMax = rightMax;
			this.max = max;
		}
	}

	static Node[] seg;

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[][] org = new int[n][3];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			org[i][0] = Integer.parseInt(st.nextToken());
			org[i][1] = Integer.parseInt(st.nextToken());
			org[i][2] = Integer.parseInt(st.nextToken());
		}
		//좌표 압축
		forge(org, n);
		//좌표를 기반으로 2차원 배열 생성
		//동시에, 세그 트리를 갱신할 때 사용할 연결리스트를 생성한다
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}

		long[][] ary = new long[N][M];
		for (int k = 0; k < n; k++) {
			int i = org[k][1];
			int j = org[k][0];
			ary[i][j] = org[k][2];

			//연결리스트에 값 추가해준다
			if (ary[i][j] != 0)
				graph.get(i).add(j);

		}
		//x축에 대하여 대칭된 모습 확인
		//printAry(ary);

		//DP배열을 생성한다
		//합을 누적한다(각 열에 대하여 값을 누적해서 아래로 더한다)
		// long[][] dp = new long[N][M];
		// for (int j = 0; j < M; j++) {
		// 	dp[0][j] = ary[0][j];
		// }
		// for (int i = 1; i < N; i++) {
		// 	for (int j = 0; j < M; j++) {
		// 		dp[i][j] = dp[i - 1][j] + ary[i][j];
		// 	}
		// }
		//DP배열 확인
		// printAry(dp);

		//이중 포문을 이용해 모든 행을 두개씩 고른다
		//윗 행을 고른 시점에 세그 트리를 생성한다
		//아랫행이 하나씩 늘어날때마다 세그트리를 갱신한다
		long answer = 0;
		for (int upper = 0; upper < N; upper++) {
			//세그 트리 생성
			init();
			for (int lower = upper; lower < N; lower++) {
				//갱신할 것은 갱신한다
				//다만 M번 탐색해서 갱신할 위치를 찾으면 시간 복잡도가 N^3이 된다
				//연결리스트를 활용해서 해당 위치에 있는 노드를 파악해서 값을 더한다
				for (Integer j : graph.get(lower)) {
					long value = ary[lower][j];//더할값
					update(0,M-1,1,j,value);
				}
				//해당 행에 대하여 갱신 완료
				//최대값 추출
				long max = seg[1].max;
				answer = Math.max(max,answer);

			}
		}
		System.out.println(answer);
	}



	private static void update(int s, int e, int idx, int c, long val) {
		if (c < s || e < c)
			return;
		if (s == e) {
			//리프노드에 값을 더한다
			seg[idx].max += val;
			seg[idx].leftMax += val;
			seg[idx].rightMax += val;
			seg[idx].sum += val;
			return;
		}
		int mid = (s + e) >> 1;
		update(s, mid, idx * 2, c, val);
		update(mid + 1, e, idx * 2 + 1, c, val);
		Node left = seg[idx * 2];
		Node right = seg[idx * 2 + 1];
		seg[idx].sum = left.sum + right.sum;
		seg[idx].leftMax = Math.max(left.leftMax, left.sum + right.leftMax);
		seg[idx].rightMax = Math.max(right.rightMax, right.sum + left.rightMax);
		seg[idx].max = Math.max(Math.max(left.max, right.max), left.rightMax + right.leftMax);
		//세그트리 업데이트 완료
	}

	private static void init() {
		seg = new Node[4 * M];
		for (int idx = 0; idx < 4 * M; idx++) {
			seg[idx] = new Node(0, 0, 0, 0);
		}
	}

	private static void printAry(long[][] ary) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(ary[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void forge(int[][] ary, int n) {
		//좌표 압축 x좌표(가로 좌표 j)
		Arrays.sort(ary, (o1, o2) -> Integer.compare(o1[0], o2[0]));
		int x = -1;
		int before = -1;
		for (int i = 0; i < n; i++) {
			//이전 수와 다르다면
			if (ary[i][0] != before) {
				before = ary[i][0];
				ary[i][0] = ++x;

			} else {
				ary[i][0] = x;
			}
		}
		M = x + 1;
		//좌표 압축 y좌표(세로 좌표 i)
		Arrays.sort(ary, (o1, o2) -> Integer.compare(o1[1], o2[1]));
		int y = -1;
		before = -1;
		for (int i = 0; i < n; i++) {
			//이전 수와 다르다면
			if (ary[i][1] != before) {
				before = ary[i][1];
				ary[i][1] = ++y;

			} else {
				ary[i][1] = y;
			}
		}
		N = y + 1;
	}

}